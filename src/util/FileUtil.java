package util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Create by Allen
 * Date: 2019/8/11
 * Time: 21:25
 */
public class FileUtil {

    public static List<String> getFileContent(String fileName){
        List<String> list = new ArrayList<>();
        try {
            String str = null;
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            while ((str = reader.readLine()) != null){
                list.add(str);
            }
            reader.close();
            return list;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void writeToFile(List<String> list, String fileName){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            for(String content : list){
                writer.write(content, 0, content.length());
                writer.newLine();
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
