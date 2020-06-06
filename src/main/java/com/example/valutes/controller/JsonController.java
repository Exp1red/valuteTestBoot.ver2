package com.example.valutes.controller;

import com.example.valutes.repos.ValuteRepo;
import com.example.valutes.util.ValuteHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class JsonController {
    final
    ValuteRepo valuteRepo;

    public JsonController(ValuteRepo valuteRepo) {
        this.valuteRepo = valuteRepo;
    }

    @GetMapping("/getDate")
    public List<Date> getDate() {
        return valuteRepo.getSortedDate();
    }

    @GetMapping("/getRatio{charCode}")
    public List<Double> getRatio(@PathVariable("charCode") String charCode ) {

        ValuteHelper.getRatio( charCode , "EUR" , "CNY" , "JPY");

    }



}
