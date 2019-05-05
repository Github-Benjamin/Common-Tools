package Utils;

import java.io.*;

/**
 * Created by Benjamin on 2019/5/5.
 */
public class TxtFileUtil {
    public static void WriteStringToFile(String filename,String nowTime,String content) {
        try {
            String filePath = filename + nowTime + ".txt";
            File file = new File(filePath);

            // 存储操作
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
            bw.write(content);  //将内容写到fileUrl中
            bw.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
