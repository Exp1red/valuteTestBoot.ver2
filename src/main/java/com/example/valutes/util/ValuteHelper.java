package com.example.valutes.util;

import com.example.valutes.entities.Valute;
import com.example.valutes.jsonpojo.Json;
import com.example.valutes.repos.ValuteRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ValuteHelper {
    final
    ValuteRepo valuteRepo;

    public ValuteHelper(ValuteRepo valuteRepo) {
        this.valuteRepo = valuteRepo;
    }

    public  void getStatisticOfValuteForMonth(int countOfDays, String originalUrl) {

        if (countOfDays < 31) {

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            new URL(originalUrl).openStream()))) {

                ObjectMapper objectMapper = new ObjectMapper();
                Json json = objectMapper.readValue(reader, Json.class);

                Map<String, Valute> map = json.getValutes();
                List<Valute> list = getSelection(map, "USD", "EUR", "CNY", "JPY");

                for (Valute val : list) {
                    val.setDate(json.getDate());
                    valuteRepo.save(val);
                }

                getStatisticOfValuteForMonth(countOfDays + 1, "https:" + json.getPreviousUrl());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public  Map<String , List <Double>> getRatio(String charCode ,
                                                       String ... charCodesOfOtherValutes){

        Map<String , List<Double>> resultMap = new HashMap<>();
        List<Double> listOfValue = valuteRepo.getConcreteValueOrderByValute(charCode);

        for (String str : charCodesOfOtherValutes) {

        }

//        new BigDecimal(valute.getValue() / (otherValute.getValue() )
//                .setScale(4 , RoundingMode.UP).doubleValue());



    }


    public static List<Valute> getSelection(Map<String, Valute> map, String... args) {
        List<Valute> list = new ArrayList<>();
        for (String arg : args) {
            list.add(map.get(arg));
        }
        return list;
    }
}
