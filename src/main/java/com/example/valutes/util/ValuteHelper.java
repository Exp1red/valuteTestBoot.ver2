package com.example.valutes.util;

import com.example.valutes.entities.Valute;
import com.example.valutes.jsonpojo.IncomeJsonAPI;
import com.example.valutes.repos.ValuteRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class ValuteHelper {
    final ValuteRepo valuteRepo;

    public ValuteHelper(ValuteRepo valuteRepo) {
        this.valuteRepo = valuteRepo;
    }

    public void getStatisticOfValuteForMonth(int countOfDays, String originalUrl) {

        if (countOfDays > 0) {

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            new URL(originalUrl).openStream()))) {

                ObjectMapper objectMapper = new ObjectMapper();
                IncomeJsonAPI json = objectMapper.readValue(reader, IncomeJsonAPI.class);

                Map<String, Valute> map = json.getValutes();
                List<Valute> list = getSelection(map, "USD", "EUR", "CNY", "JPY");

                for (Valute val : list) {
                    val.setDate(json.getDate());
                    valuteRepo.save(val);
                }

                getStatisticOfValuteForMonth(countOfDays - 1, "https:" + json.getPreviousUrl());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    public Map<String, List<Double>> getRatio(String charCode, String[] charCodesOfOtherValutes) {

        Map<String, List<Double>> resultMapOfTotalRatio = new LinkedHashMap<>();
        List<Double> listOfValue = valuteRepo.getConcreteValue(charCode);

        for (String str : charCodesOfOtherValutes) {
            if (!str.equals(charCode)) {
                List<Double> listOfRatio = new ArrayList<>();

                int i = 0;
                List<Double> concreteValue = valuteRepo.getConcreteValue(str);

                for (Double otherValue : concreteValue) {

                    listOfRatio.add(new BigDecimal(listOfValue.get(i) / otherValue)
                            .setScale(4, RoundingMode.UP).doubleValue());

                    i++;
                }
                resultMapOfTotalRatio.put(str, listOfRatio);
            }
        }
        return resultMapOfTotalRatio;       // TODO: 06.06.2020 change logic to STREAM and need proxyCache and Logger ETC
    }


    public static List<Valute> getSelection(Map<String, Valute> map, String... args) {
        List<Valute> list = new ArrayList<>();
        for (String arg : args) {
            list.add(map.get(arg));
        }
        return list;
    }

    public String[] getArrayOfCharCodes() {
        return new String[]{"USD", "EUR", "CNY", "JPY"};
    }
}
