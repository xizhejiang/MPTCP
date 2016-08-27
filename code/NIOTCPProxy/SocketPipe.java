package NIOTCPProxy;

import java.io.IOException;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;

public class SocketPipe{


    private SocketChannel client;
        private ByteBuffer buffer;
        private SocketChannel dst;
        public SocketPipe(ByteBuffer buffer,SocketChannel client,SocketChannel dst){
            this.client = client;
            this.buffer = buffer;
            this.dst = dst;
        }
        public SocketChannel getClient() {
            return client;
        }

        public ByteBuffer getBuffer() {
            return buffer;
        }

        public SocketChannel getDst() {
            return dst;
        }
        public void setClient(SocketChannel client) {
            this.client = client;
        }

        public void setBuffer(ByteBuffer buffer) {
            this.buffer = buffer;
        }

        public void setDst(SocketChannel dst) {
            this.dst = dst;
        }

    public static SocketChannel createOutLink(InetSocketAddress dst,SelectionKey key){
        SocketChannel outChannel = null;
        try {
            Selector selector = key.selector();
            outChannel = SocketChannel.open();
            if(outChannel!=null){
                outChannel.configureBlocking(false);
                outChannel.connect(dst);
                while (!outChannel.finishConnect()) { }
                outChannel.register(selector, SelectionKey.OP_READ);
                return outChannel;
            }

        }catch(ConnectException e){
            if(outChannel!=null){
                try {
                    outChannel.close();
                    key.cancel();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static SocketChannel incomingChannel(SelectionKey key) {
        try {
            ServerSocketChannel channel = (ServerSocketChannel) key.channel();
            Selector selector = key.selector();
            SocketChannel inChannel = channel.accept();
            if(inChannel!=null){
                inChannel.configureBlocking(false);
                inChannel.register(selector, SelectionKey.OP_READ);
            }
            return inChannel;
        } catch (ClosedSelectorException | IOException e) {
            System.out.println("Incoming connection has failed " + e);
            return null;
        }
    }

    }