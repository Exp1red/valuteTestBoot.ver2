package com.example.valutes.jsonpojo;

import java.util.List;
import java.util.Map;

public class ForChartJsonComplex {

    private List<String> date;

    private Map<String , List <Double>> ratios ;

    public List<String> getDate() {
        return date;
    }

    public void setDate(List<String> date) {
        this.date = date;
    }

    public Map<String, List<Double>> getRatios() {
        return ratios;
    }

    public void setRatios(Map<String, List<Double>> ratios) {
        this.ratios = ratios;
    }
}
