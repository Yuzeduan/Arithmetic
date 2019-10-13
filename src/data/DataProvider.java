package data;

import util.DecimalUtil;

import java.util.ArrayList;
import java.util.List;

import static data.Constant.EQU;

public class DataProvider {
    public static String getAnswer(String question) {
        question = question.replace(EQU, "");
        if (question.contains("(")) {
            int leftIndex = question.indexOf("(");
            int rightIndex = question.indexOf(")");
            String subQuestion = question.substring(leftIndex + 1, rightIndex);
            question = question.replace("(" + subQuestion + ")", getAnswer(subQuestion));
        }
        String[] preStrs = question.split(" ");
        List<String> strs = new ArrayList<>();
        for (int i = 1; i < preStrs.length; i += 2) {
            if (preStrs[i].equals("×")) {
                preStrs[i + 1] = DecimalUtil.toFloat(preStrs[i - 1]) * DecimalUtil.toFloat(preStrs[i + 1]) + "";
            } else if (preStrs[i].equals("÷")) {
                preStrs[i + 1] = DecimalUtil.toFloat(preStrs[i - 1]) / DecimalUtil.toFloat(preStrs[i + 1]) + "";
            } else {
                strs.add(preStrs[i - 1]);
                strs.add(preStrs[i]);
            }
            if (i == preStrs.length - 2) {
                strs.add(preStrs[i + 1]);
            }
        }
        if (strs.size() == 1) {
            return DecimalUtil.toStr(Float.valueOf(strs.get(0)));
        }
        for (int i = 1; i < strs.size(); i += 2) {
            if (strs.get(i).equals("+")) {
                strs.set(i + 1, DecimalUtil.toFloat(strs.get(i - 1)) + DecimalUtil.toFloat(strs.get(i + 1)) + "");
            } else {
                float temp = DecimalUtil.toFloat(strs.get(i - 1)) - DecimalUtil.toFloat(strs.get(i + 1));
                if(temp < 0) return null;
                strs.set(i + 1, temp + "");
            }
            if (i == strs.size() - 2) {
                return DecimalUtil.toStr(Float.valueOf(strs.get(i + 1)));
            }
        }
        return null;
    }
}
