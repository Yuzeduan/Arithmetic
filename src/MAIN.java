import service.ServiceManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by Allen
 * Date: 2019/10/11
 * Time: 15:42
 */
public class MAIN {
    public static void main(String[] args) {
        String[] testSet = new String[]{"-n", "5", "-r", "10"};
//        String[] testSet = new String[]{"-e", "./Exercises.txt", "-a", "./Answers.txt"};
        ServiceManager.getInstance(testSet).getService().handleRequest();
    }
}
