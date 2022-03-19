package com.example.currencyrate;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Valute {

    private int countryFlag;
    private int arrow;
    private String countryId;
    private Double currentRate;
    private Double previousRate;
    private double difference;

    public Valute() {
    }



    public int getCountryFlag() {
        return countryFlag;
    }

    public void setCountryFlag(int countryFlag) {
        this.countryFlag = countryFlag;
    }

    public int getArrow() {
        return arrow;
    }

    public void setArrow(int arrow) {
        this.arrow = arrow;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public Double getCurrentRate() {
        return currentRate;
    }

    public void setCurrentRate(Double currentRate) {
        this.currentRate = currentRate;
    }

    public Double getDifference() {
        return difference;
    }


    public Double getPreviousRate() {
        return previousRate;
    }

    public void setPreviousRate(Double previousRate) {
        this.previousRate = previousRate;
    }

    public void setDifference(Double currentRate, Double previousRate) {
        this.difference = currentRate - previousRate;
    }
}
