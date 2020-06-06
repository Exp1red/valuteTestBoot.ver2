package com.example.valutes.controller;


import com.example.valutes.repos.ValuteRepo;
import com.example.valutes.util.Helper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {

    private final ValuteRepo valuteRepo;

    public MainController(ValuteRepo valuteRepo) {
        this.valuteRepo = valuteRepo;
    }

    @GetMapping("/parse")
    public String getAndParse() {
        Helper.recursion(1, valuteRepo, "https://www.cbr-xml-daily.ru/daily_json.js");
        return "parse";
    }


    @GetMapping("/chart")
    public String chart() {
        return "chart";
    }


}
