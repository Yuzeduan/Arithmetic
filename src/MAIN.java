import service.ServiceManager;

/**
 * Create by Allen
 * Date: 2019/10/11
 * Time: 15:42
 */
public class MAIN {
    public static void main(String[] args) {
        ServiceManager.getInstance(new String[]{"-n", "10000", "-r", "10"}).getService().handleRequest();
    }
}
