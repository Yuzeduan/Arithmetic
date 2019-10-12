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
    private BufferedReader inputStream = null;
    private PrintWriter outputStream = null;

    public int initInputStream(String fileName) {
        try {
            inputStream = new BufferedReader(new FileReader(fileName));
            return 0;
        } catch (FileNotFoundException e) {
            System.out.println("打开文件失败");
            return -1;
        }
    }

    public int initOutputStream(String fileName) {
        try {
            outputStream = new PrintWriter(fileName);
            return 0;
        } catch (FileNotFoundException e) {
            System.out.println("打开文件失败");
            return -1;
        }
    }

    public void finishInputStream() {
        try {
            inputStream.close();
        } catch (IOException e) {
            System.out.println("关闭文件失败");
        }
    }

    public void finishOutputStream() {
        outputStream.flush();
        outputStream.close();
    }

    public String read(String fileName) {
        try {
            String data = inputStream.readLine();
            if (data != null) {
                return data;
            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void write(String data) {
        outputStream.println(data);
    }
}
