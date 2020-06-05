package com.example.valutes.controller;

import com.example.valutes.repos.ValuteRepo;
import com.example.valutes.util.Helper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    private final ValuteRepo valuteRepo;

    public MainController(ValuteRepo valuteRepo) {
        this.valuteRepo = valuteRepo;
    }

    @GetMapping("/parse")
    public void getAndParse() {
        Helper.recursion(1 , valuteRepo , "https://www.cbr-xml-daily.ru/daily_json.js");
    }

    @GetMapping("/chart")
    public void getChart(){
        
    }

}
