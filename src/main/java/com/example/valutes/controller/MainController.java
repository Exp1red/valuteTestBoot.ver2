package com.example.valutes.controller;


import com.example.valutes.util.ValuteHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {

    private final ValuteHelper valuteHelper;


    public MainController(ValuteHelper valuteHelper) {
        this.valuteHelper = valuteHelper;

    }

    @GetMapping("/fill")
    public String getAndParse() {
        valuteHelper.getStatisticOfValuteForMonth(30, "https://www.cbr-xml-daily.ru/daily_json.js");
        return "fillDataBase";
    }



    @GetMapping("/")
    public String home(){
        return "home";
    }


    @GetMapping("/usd_chart")
    public String chartUsd() {
        return "chartOfRatioUSD.html";
    }

    @GetMapping("/eur_chart")
    public String chartEur() {
        return "chartOfRatioEUR.html";
    }

    @GetMapping("/cny_chart")
    public String chartCny() {
        return "chartOfRatioCNY.html";
    }

    @GetMapping("/jpy_chart")
    public String chartJpy() {
        return "chartOfRatioJPY.html";
    }


}
