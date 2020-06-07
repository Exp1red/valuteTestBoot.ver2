package com.example.valutes.jsonpojo;

public class ForChartJsonOne {

    public ForChartJsonOne(String date, double ratio1, double ratio2, double ratio3) {
        this.date = date;
        this.ratio1 = ratio1;
        this.ratio2 = ratio2;
        this.ratio3 = ratio3;
    }


    private String date;

    private double ratio1;
    private double ratio2;
    private double ratio3;

    public double getRatio1() {
        return ratio1;
    }

    public void setRatio1(double ratio1) {
        this.ratio1 = ratio1;
    }

    public double getRatio2() {
        return ratio2;
    }

    public void setRatio2(double ratio2) {
        this.ratio2 = ratio2;
    }

    public double getRatio3() {
        return ratio3;
    }

    public void setRatio3(double ratio3) {
        this.ratio3 = ratio3;
    }

    private String charCode;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCharCode() {
        return charCode;
    }

    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }


}
