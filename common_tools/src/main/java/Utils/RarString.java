package Utils;

/**
 * Created by Benjamin on 2019/5/19.
 */

public class RarString {

    public static String RarString(String str){
        str = str.replaceAll("\\n", "");
        str = str.replaceAll("\\t", "");
        str = str.replaceAll("\\r", "");
        return str;
    }

}
