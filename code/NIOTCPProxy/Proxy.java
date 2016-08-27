package NIOTCPProxy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by AlexJIANG on 8/25/16.
 */
public class Proxy {
    private InetSocketAddress local;
    private InetSocketAddress dst;
    private final int TIMEOUT = 2000;
    private Selector selector;
    private ServerSocketChannel ssc;


    private Map<SocketChannel, SocketPipe> firstLayerCache;
    private Map<SocketChannel,SocketPipe> sencondLayerCache;
    private LogUtil logUtil;
    private int numofThreads;
    public Proxy(InetSocketAddress local,InetSocketAddress dst){

        try {
            this.local = local;
            this.dst = dst;
            selector = Selector.open();
            ssc = ServerSocketChannel.open();
            ssc.configureBlocking(false);
            ssc.socket().bind(local);
            ssc.register(selector, SelectionKey.OP_ACCEPT);
            firstLayerCache = new ConcurrentHashMap<>();
            sencondLayerCache = new ConcurrentHashMap<>();
            logUtil = new LogUtil();
            this.numofThreads = Runtime.getRuntime().availableProcessors();


        } catch (IOException e) {
            e.printStackTrace();
            terminate();
        }

    }



    public Integer startProxy() throws Exception {
        logUtil.log("start listening...");
        ThreadFactory<Integer> threadFactory = new SingleThreadFactory<Integer>();
        final ExecutorService executorService = threadFactory.create();
        //final ExecutorService executorService = Executors.newSingleThreadExecutor();
        try{
            while(selector.isOpen()){
                if(selector.select(TIMEOUT) == 0){
                    continue;
                }
                Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
                while(keyIterator.hasNext()){
                    SelectionKey key = keyIterator.next();
                    keyIterator.remove();
                    executorService.submit(new Handler(firstLayerCache,sencondLayerCache,key,dst));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            terminate();
            clearCache();
        }

        return null;
    }


    public void terminate(){
        try {
            ssc.socket().close();
            ssc.close();
            selector.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void clearCache(){
        firstLayerCache.clear();
        sencondLayerCache.clear();
    }



    public Map<SocketChannel, SocketPipe> getFirstLayerCache() {
        return firstLayerCache;
    }

    public Map<SocketChannel, SocketPipe> getSencondLayerCache() {
        return sencondLayerCache;
    }

}
