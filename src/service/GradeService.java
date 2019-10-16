package service;

import data.DataProvider;
import util.FileUtil;

import java.util.ArrayList;
import java.util.List;

public class GradeService extends BaseService {
    @Override
    public void handleRequest() {
        FileUtil questionFile = new FileUtil();
        FileUtil answerFile = new FileUtil();
        FileUtil gradeFile = new FileUtil();

        if (questionFile.initInputStream(args[0]) == -1) return;
        if (answerFile.initInputStream(args[1]) == -1) return;
        if (gradeFile.initOutputStream("./Grade.txt") == -1) return;
        List<String> correct = new ArrayList<>();
        List<String> wrong = new ArrayList<>();
        String question = questionFile.read();
        String answer = answerFile.read();
        while (question != null && answer != null) {
            String[] quesStrs = question.split("\\. ");
            if (quesStrs.length == 2) {
                String[] ansStrs = answer.split("\\. ");
                if (ansStrs.length == 2) {
//                    System.out.println(DataProvider.getAnswer(quesStrs[1]) + " : " + ansStrs[1]);
                    if (DataProvider.getAnswer(quesStrs[1]).equals(ansStrs[1])) {
                        correct.add(quesStrs[0]);
                    } else {
                        wrong.add(quesStrs[0]);
                    }
                } else {
                    System.out.println("有答案出错");
                    continue;
                }
            } else {
                System.out.println("有题目出错");
                continue;
            }
            question = questionFile.read();
            answer = answerFile.read();
        }
        gradeFile.write("Correct: " + correct.size() + " (" + String.join(", ", correct) + ")");
        gradeFile.write("Wrong: " + wrong.size() + " (" + String.join(", ", wrong) + ")");
        questionFile.finishInputStream();
        answerFile.finishInputStream();
        gradeFile.finishOutputStream();
    }

    public GradeService(String[] args) {
        super(args);
    }
}
