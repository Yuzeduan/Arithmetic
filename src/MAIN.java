import service.ServiceManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Create by Allen
 * Date: 2019/10/11
 * Time: 15:42
 */
public class MAIN {
    public static void main(String[] args) {
        Date before = new Date();
        ServiceManager.getInstance(args).getService().handleRequest();
        System.out.println("生成完毕，耗时 " + (new Date().getTime() - before.getTime()) / 1000.0f + " 秒");
    }
}
