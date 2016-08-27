package NIOTCPProxy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * Created by AlexJIANG on 8/27/16.
 */
public class Handler implements Callable<Integer> {
    private final int BUF_SIZE = 256*1024;
    private Map<SocketChannel,SocketPipe> sencondLayerCache;
    private Map<SocketChannel,SocketPipe> firstLayerCache;
    private LogUtil logUtil;
    private SelectionKey key;
    private InetSocketAddress dst;
    public Handler(Map<SocketChannel,SocketPipe> firstLayerCache, Map<SocketChannel,SocketPipe> secondLayerCache
    , SelectionKey key, InetSocketAddress dst){
        this.firstLayerCache = firstLayerCache;
        this.sencondLayerCache = secondLayerCache;
        this.dst = dst;
        this.key = key;
        logUtil = new LogUtil();
    }

    private void handleWrite(SelectionKey key, Map<SocketChannel,SocketPipe> sencondLayerCache){
        SocketPipe sp = sencondLayerCache.get(key.channel());
        Selector selector = key.selector();
        if(sp!=null){
            ByteBuffer buffer = sp.getBuffer();
            buffer.flip();
            try{
                sp.getDst().write(buffer);
                //LogUtil.testLog(buffer);
                if(buffer.hasRemaining()){
                    buffer.compact();
                    return;
                }
                buffer.clear();
                sp.getDst().register(selector,SelectionKey.OP_READ);
                sp.getClient().register(selector,SelectionKey.OP_READ);
                sencondLayerCache.remove(sp.getDst());
            } catch (IOException e) {
                e.printStackTrace();
                giveup(sp);
            }
        }else{
            //logUtil.log("socket pipe is null");
        }
    }
    @Override
    public Integer call() throws Exception {
        return handle(key);

    }

    private int handle(SelectionKey key) {
        if(key.isValid()&&key.isAcceptable()){
            logUtil.showCurrentThreadInfo("accepting");
            handleAccept(key);
            //return 1;
            return 1;
        }
        if(key.isValid()&&key.isReadable()){
            logUtil.showCurrentThreadInfo("reading");
            handleRead(key);
            //return 2;
            return 2;
        }

        if(key.isValid()&&key.isWritable()){
            logUtil.showCurrentThreadInfo("writing");
            handleWrite(key,sencondLayerCache);
            //return;
            return 3;
        }
        return -1;
    }

    public void giveup(SocketPipe sp){
        firstLayerCache.remove(sp.getClient());
        firstLayerCache.remove(sp.getDst());
        sencondLayerCache.remove(sp.getClient());
        sencondLayerCache.remove(sp.getDst());
    }

    private void handleRead(SelectionKey key) {
        SocketPipe sp = firstLayerCache.get((SocketChannel) key.channel());
        if(sp!=null){
            try {
                synchronized (sp.getBuffer()){
                    sp.getClient().read(sp.getBuffer());
                }
                //LogUtil.testLog(sp.getBuffer());
                 writeToOtherServer(sp);
            } catch (IOException e) {
                giveup(sp);
                e.printStackTrace();
            }
        }
    }

    private void writeToOtherServer(SocketPipe sp) throws IOException {
        ByteBuffer buffer = sp.getBuffer();
        Selector selector = key.selector();
        if(!sp.getDst().isConnected()){
            sp.getDst().connect(dst);
            while (!sp.getDst().finishConnect()) { }
        }
        buffer.flip();
        sp.getDst().write(buffer);
        if(buffer.hasRemaining()){
            //LogUtil.log("has remaining");
            sp.getClient().register(selector,0);
            buffer.compact();
            sp.getDst().register(selector,SelectionKey.OP_READ|SelectionKey.OP_WRITE);
            sencondLayerCache.put(sp.getDst(),sp);
        }else{
            //LogUtil.testLog(buffer);
            buffer.clear();
        }
    }

    private void handleAccept(SelectionKey key) {
        try {
            final SocketChannel client = SocketPipe.incomingChannel(key);
            if(client!=null){
                final SocketChannel out = SocketPipe.createOutLink(dst,key);
                if(dst == null){
                    System.out.println("client closed");
                    client.close();
                    return;
                }
                //for client incomming link
                //logUtil.log("arrive here");
//                client.configureBlocking(false);
//                System.out.println("before + "+key.selector().selectedKeys().size());
//                client.register(selector,SelectionKey.OP_READ);
//                //ssc.register(selector,SelectionKey.OP_READ);
//                System.out.println("after + "+key.selector().selectedKeys().size());

                firstLayerCache.put(client,new SocketPipe(ByteBuffer.allocate(BUF_SIZE),client,out));
                firstLayerCache.put(out,new SocketPipe(ByteBuffer.allocate(BUF_SIZE),out,client));

            }else{
                //logUtil.log("socket pipe is null");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
