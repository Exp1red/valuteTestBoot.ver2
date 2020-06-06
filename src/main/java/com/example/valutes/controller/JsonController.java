package com.example.valutes.controller;

import com.example.valutes.entities.Valute;
import com.example.valutes.repos.ValuteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
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
        List<Date> listOfDate = new LinkedList<>();

        for (Valute val : valuteRepo.findByNumCodeOrderByDateAsc(840)) {
            listOfDate.add(val.getDate());
        }

        return listOfDate;
    }

//    @GetMapping("/getRatio{numCode}")
//    public List<Double> getRatio(@PathVariable(name = "numCode") int a) {
//
//            List<Double> listOfValue = new ArrayList<>();
//
//            for (Valute val : valuteRepo.findByNumCodeOrderByDateAsc(a)) {
//                listOfValue.add(val.getValue());
//            }
//
//
////       new BigDecimal(valute.getValue() / (otherValute.getValue() )
////                                .setScale(4 , RoundingMode.UP).doubleValue());
//
//    }



}
