package service;

import data.Constant;
import data.DataProvider;
import data.bean.Number;
import util.DecimalUtil;
import util.FileUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static data.Constant.ADD;
import static data.Constant.SUB;
import static data.Constant.MUL;
import static data.Constant.DIV;
import static data.Constant.EQU;

public class QuestionService extends BaseService {
    @Override
    public void handleRequest() {
        int num = Integer.parseInt(args[0]);
        int max = Integer.parseInt(args[1]);
        String[] SymbolList = new String[]{ADD, SUB, MUL, DIV};

        FileUtil questionFile = new FileUtil();
        FileUtil answerFile = new FileUtil();
        questionFile.initOutputStream("./Exercises.txt");
        answerFile.initOutputStream("./Answers.txt");


        for (int question = 0; question < num; question++) {
            Random random = new Random();
            List<Number> nums = new ArrayList<>();

            // 生成运算符数量
            int operator = random.nextInt(3) + 1;
            boolean isInt = false;
            boolean hasParentheses = false;
            String symbol = "";
            float operateNum = -1;
            for (int i = 0; i < operator + 1; i++) {
                isInt = random.nextInt(2) == Constant.TYPE_INT;
                symbol = i == 0 ? "" : SymbolList[random.nextInt(4)];
                if (symbol.equals(SUB)) {
                    operateNum = isInt ? random.nextInt((int) (nums.get(i - 1).getNum()) + 1) : random.nextInt((int) ((nums.get(i - 1).getNum() + 0.01) * 100)) / 100.0f;
                } else {
                    operateNum = isInt ? random.nextInt(max + 1) : random.nextInt((max + 1) * 100) / 100.0f;
                }
                if (symbol.equals(DIV)) {
                    operateNum = operateNum == 0 ? (isInt ? 1 : 0.01f) : operateNum;
                }
                nums.add(new Number(symbol, operateNum));
            }
            hasParentheses = random.nextInt(2) == 1;
            int leftIndex = -1;
            int rightIndex = -1;
            if (hasParentheses) {
                leftIndex = random.nextInt(operator);
                rightIndex = random.nextInt(operator - leftIndex) + leftIndex + 1;
            }
            StringBuffer sb = new StringBuffer();
            Number number;
            for (int i = 0; i < nums.size(); i++) {
                number = nums.get(i);
                if (hasParentheses && i == leftIndex) {
                    sb.append(number.getSymbol())
                            .append("(")
                            .append(DecimalUtil.toStr(number.getNum()));
                } else if (i == rightIndex) {
                    sb.append(number.getSymbol())
                            .append(DecimalUtil.toStr(number.getNum()))
                            .append(")");
                } else {
                    sb.append(number.getSymbol()).append(DecimalUtil.toStr(number.getNum()));
                }
            }
            sb.append(EQU);
            String answer = DataProvider.getAnswer(sb.toString());
            if (answer == null) {
                question--;
                continue;
            }
            questionFile.write(question + 1 + ". " + sb.toString());
            answerFile.write(question + 1 + ". " + answer);
            System.out.println(sb.toString());
            System.out.println(DataProvider.getAnswer(sb.toString()));
        }
        questionFile.finishOutputStream();
        answerFile.finishOutputStream();
    }

    public QuestionService(String[] args) {
        super(args);
    }
}
