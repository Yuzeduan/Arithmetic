package service;

public class ServiceManager {
    private static ServiceManager INSTANCE;
    private String[] args;

    private ServiceManager(String[] args) {
        this.args = args;
    }

    public static ServiceManager getInstance(String[] args) {
        if (INSTANCE == null) {
            synchronized (ServiceManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ServiceManager(args);
                }
            }
        }
        return INSTANCE;
    }

    public BaseService getService(){
        int mode = -1;
        String questionNum = "1";
        String digMax = "";
        String exerciseFilePath = "";
        String answerFIlePath = "";
        for (int i = 0; i < args.length; i++) {
            try {
                switch (args[i]) {
                    case "-n":
                        mode = 0;
                        questionNum = args[i+1];
                        i++;
                        break;
                    case "-r":
                        mode = 0;
                        digMax = args[i+1];
                        i++;
                        break;
                    case "-e":
                        mode = 1;
                        exerciseFilePath = args[i+1];
                        i++;
                        break;
                    case "-a":
                        mode = 1;
                        answerFIlePath = args[i+1];
                        i++;
                        break;
                }
            } catch (IndexOutOfBoundsException e) {
                printErrorMsg("参数错误");
            }
        }
        if (mode == -1) {
            printErrorMsg("没有指定 -r 参数！");
            return null;
        } else {
            return mode == 0 ? new QuestionService(new String[]{questionNum, digMax}): new GradeService(new String[]{exerciseFilePath, answerFIlePath});
        }
    }

    private void printErrorMsg(String Msg) {
        System.out.println(Msg);
    }
}
