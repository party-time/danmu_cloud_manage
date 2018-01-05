package cn.partytime.util;

import java.io.*;

/**
 * Created by admin on 2018/1/5.
 */
public class FileUtil {

    public static void writeFile(String filePath,String content) throws IOException {
        File fout = new File(filePath);
        FileOutputStream fos = new FileOutputStream(fout);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
        bw.write(content);
        bw.close();
    }

}
