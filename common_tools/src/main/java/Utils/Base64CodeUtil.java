package Utils;

import org.apache.commons.codec.binary.Base64;

/**
 * Created by Benjamin on 2019/5/5.
 */

public class Base64CodeUtil {

    public static String ENCode(String str){
        byte[] result = Base64.encodeBase64(str.getBytes());
        return new String(result);
    }

    public static String DECode(String str){
        byte[] result = Base64.decodeBase64(str.getBytes());
        return new String(result);
    }

}
