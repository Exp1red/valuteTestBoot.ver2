package com.example.valutes.controller;


import com.example.valutes.util.ValuteHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {
    private static final Logger log = LoggerFactory.getLogger(MainController.class);
    private final ValuteHelper valuteHelper;


    public MainController(ValuteHelper valuteHelper) {
        this.valuteHelper = valuteHelper;
    }

    @GetMapping("/fill")
    public String getAndParse() {
        valuteHelper.getStatisticOfValuteForMonth(30, "https://www.cbr-xml-daily.ru/daily_json.js");
        log.debug("fill data base has been complete");
        return "fillDataBase";
    }



    @GetMapping("/")
    public String home(){
        return "home";
    }


    @GetMapping("/usd_chart")
    public String chartUsd() {
        log.info("selected chart of usd");
        return "chartOfRatioUSD.html";
    }

    @GetMapping("/eur_chart")
    public String chartEur() {
        log.info("selected chart of eur");
        return "chartOfRatioEUR.html";
    }

    @GetMapping("/cny_chart")
    public String chartCny() {
        log.info("selected chart of cny");
        return "chartOfRatioCNY.html";
    }

    @GetMapping("/jpy_chart")
    public String chartJpy() {
        log.info("selected chart of jpy");
        return "chartOfRatioJPY.html";
    }


}
