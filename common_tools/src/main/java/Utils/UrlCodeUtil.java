package Utils;


/**
 * Created by Benjamin on 2019/4/30.
 */
public class UrlCodeUtil {

    public static String EnCode(String str){
        String encode = java.net.URLEncoder.encode(str);
        System.out.println(encode);
        return encode;
    }

    public static String DECode(String str){
        try {
            String decode = java.net.URLDecoder.decode(str);
            System.out.println(decode);
            return decode;
        } catch (Exception e) {
            return "data error";
        }

    }

}
