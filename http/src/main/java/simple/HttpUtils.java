package simple;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/**
 * Created by Alexander on 22.04.2015.
 *
 */
public class HttpUtils {
    public static byte[] readRequestFully(InputStream in) throws IOException {
        byte[] buff = new byte[8192];
        int headerLen = 0;
        while (true){
            int count = in.read(buff,headerLen,buff.length - headerLen);
            if(count<0){
                throw new RuntimeException("Incoming connection close");
            }else {
                headerLen += count;
                if(isRequestEnd(buff,headerLen)){
                    return Arrays.copyOfRange(buff,0,headerLen);
                }
                if(headerLen == buff.length){
                    throw new RuntimeException("Too big HTTP header");
                }
            }
        }
    }

    // \r \n end of request two time
    private static boolean isRequestEnd(byte[] buff, int headerLen) {
        if(headerLen <4){
            return false;
        }
        return buff[headerLen-4] == '\r' &&
                buff[headerLen-3] == '\n' &&
                buff[headerLen-2] == '\r' &&
                buff[headerLen-1] == '\n';

    }
}
