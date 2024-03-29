package util;

public class DecimalUtil {
    public static float toFloat(String str) {
        String[] strs = str.split("’");
        if (strs.length == 1) {
            String[] twoNum = strs[0].split("/");
            return twoNum.length == 1 ? Float.valueOf(twoNum[0]) : (Float.valueOf(twoNum[0]) / Float.valueOf(twoNum[1]));
        } else {
            String[] twoNum = strs[1].split("/");
            return Integer.valueOf(strs[0]) + Float.valueOf(twoNum[0]) / Float.valueOf(twoNum[1]);
        }
    }

    public static String toStr(float dec) {
        String[] strs = (dec + "").split("\\.");
        if (strs[1].length() > 6) {
            strs[1] = strs[1].substring(0, 6);
        }
        if (strs[1].contains("E")) return null;
        int integer = Integer.valueOf(strs[0]);
        int decimal = Integer.valueOf(strs[1]);
        if (decimal == 0) return integer + "";
        int mother = (int) Math.pow(10, strs[1].length());
        int divisor = getMaxDivisor(decimal, mother);
        return (integer > 0 ? integer + "’" : "") + decimal / divisor + "/" + mother / divisor;
    }

    private static int getMaxDivisor(int a, int b) {
        int c;
        if (a < b) {
            c = a;
            a = b;
            b = c;
        }
        int r = a % b;
        while (r != 0) {
            a = b;
            b = r;
            r = a % b;
        }
        return b;
    }
}
