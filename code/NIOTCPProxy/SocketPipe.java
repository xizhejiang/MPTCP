package NIOTCPProxy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;

public class SocketPipe{
    private  static String localCharset = "UTF-8";


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

    public static InetSocketAddress getDestinationAddress(ByteBuffer buf){
        ByteBuffer buffer = ByteBuffer.allocate(buf.capacity());
        buf.put(buffer);
        buffer.flip();
        if(buffer.limit()!=buffer.position()){
            String rs = null;
            try {
                rs = Charset.forName(localCharset).newDecoder().decode(buffer).toString();
            } catch (CharacterCodingException e) {
                e.printStackTrace();
            }
            String[] requestMessage = rs.split("\r\n");
            for (String temp : requestMessage) {
                System.out.println(temp);
                if (temp.startsWith("Host")) {
                    System.out.println(temp);
                    break;
                }
            }


        }
        return null;
    }

    }