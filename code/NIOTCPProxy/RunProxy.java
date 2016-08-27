package NIOTCPProxy;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Properties;
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
        Proxy p = null;
        if(args.length == 3){
            p = new Proxy(new InetSocketAddress(Integer.parseInt(args[0])),
                new InetSocketAddress(args[1],Integer.parseInt(args[2])));
        }else{
            try{
                Properties properties = System.getProperties();
                properties.load(new FileInputStream(System.getProperty("usr.dir")+ File.separator+"argument.properties"));
                p = new Proxy(new InetSocketAddress(Integer.parseInt(properties.getProperty("localPort")))
                        , new InetSocketAddress(Integer.parseInt(properties.getProperty("remoteAddress"),Integer.parseInt(properties.getProperty("remotePort")))));
            } catch (FileNotFoundException e) {
                p = new Proxy(new InetSocketAddress(localPort),
                        new InetSocketAddress(dstHost,dstPort));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            if(null == p){
                throw new NullPointerException("please check the argument configuration");
            }else {
                p.startProxy();
            }
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
