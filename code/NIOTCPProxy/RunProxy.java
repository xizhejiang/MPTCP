package NIOTCPProxy;


import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by AlexJIANG on 8/25/16.
 */
public class RunProxy {
    private static final int localPort = 8081;
    private static final String dstHost = "www.amazon.com";
    private static final int dstPort = 80;



    public void readConfig(){

    }
    public void run(String args[]){
        Proxy p = new Proxy(new InetSocketAddress(localPort), new InetSocketAddress(dstHost,dstPort));
//        Proxy p = new Proxy(new InetSocketAddress(Integer.parseInt(args[0])),
//                new InetSocketAddress(args[1],Integer.parseInt(args[2])));
        try {
            p.startProxy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]){
        //System.out.println(args.length);
        RunProxy rp = new RunProxy();
        rp.readConfig();
        rp.run(args);

    }
}
