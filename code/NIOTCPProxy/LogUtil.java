package NIOTCPProxy;

import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;

/**
 * Created by AlexJIANG on 8/25/16.
 */
public class LogUtil {

    private  String localCharset = "UTF-8";

    public void testLog(ByteBuffer buffer) throws CharacterCodingException {
        buffer.flip();
        if(buffer.limit()!=buffer.position()){
            String rs = Charset.forName(localCharset).newDecoder().decode(buffer).toString();
            String[] requestMessage = rs.split("\r\n");
            for (String temp : requestMessage) {
                System.out.println(temp);
                if (temp.length() == 0 || temp == null) {
                    break;
                }
            }
        }
    }
    public void log(String args){
        System.out.println(args);
    }

    public void showCurrentThreadInfo(String operation){
        //System.out.println("Thread"+Thread.currentThread().getId()+" : "+operation);
    }
}
