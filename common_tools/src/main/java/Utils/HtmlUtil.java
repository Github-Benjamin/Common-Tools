package Utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * Created by Benjamin on 2019/5/5.
 */

public class HtmlUtil {

    public static String Format(String html) {
        try {
            Document doc = Jsoup.parseBodyFragment(html);
            html = doc.body().html();
            return html;
        } catch (Exception e) {
            e.printStackTrace();
            return "html data error";
        }
    }


}
