package com.example.currencyrate;

public class Converter {
    private double count;
    private double rate;

    public Converter(double count, double rate) {
        this.count = count;
        this.rate = rate;
    }

    public String convertValuteToRuble() {
        return String.valueOf(count * rate);
    }

    public String convertRubleToValute() {
        return String.valueOf(rate / count);
    }


    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
