package data.bean;

public class Number {
    private float num;
    private String symbol;

    public float getNum() {
        return num;
    }

    public void setNum(float num) {
        this.num = num;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Number(String symbol, float num) {
        this.num = num;
        this.symbol = symbol;
    }
}
