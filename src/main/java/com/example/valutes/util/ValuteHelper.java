package com.example.valutes.util;

import com.example.valutes.entities.Valute;
import com.example.valutes.jsonpojo.IncomeJsonAPI;
import com.example.valutes.repos.ValuteRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger log = LoggerFactory.getLogger(ValuteHelper.class);
    private final ValuteRepo valuteRepo;

    public ValuteHelper(ValuteRepo valuteRepo) {
        this.valuteRepo = valuteRepo;
    }

    // сбор 30 json'ов валют и сохранение в бд
    public void getStatisticOfValuteForMonth(int countOfDays, String originalUrl) {

        if (countOfDays > 0) {

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            new URL(originalUrl).openStream()))) {
                log.debug("connected to url");
                ObjectMapper objectMapper = new ObjectMapper();
                IncomeJsonAPI json = objectMapper.readValue(reader, IncomeJsonAPI.class);

                Map<String, Valute> map = json.getValutes();
                List<Valute> list = getSelection(map, "USD", "EUR", "CNY", "JPY");

                for (Valute val : list) {
                    val.setDate(json.getDate());
                    valuteRepo.save(val);
                }
                    log.debug("4 valutes of a day has been saved");

                getStatisticOfValuteForMonth(countOfDays - 1, "https:" + json.getPreviousUrl());

            } catch (IOException e) {
                log.warn("problem with url , parsing or save valute of incoming json" , e);
            }
        }
    }

    // взятие стоимости валюты и деление на стоимость других для вычисления коэфициента
    // p.s. на графиках виден скачок из-за изменения номинала одной из валют, предоставляемой json'ом
    public Map<String, List<Double>> getRatio(String charCode, String[] charCodesOfOtherValutes) {

        Map<String, List<Double>> resultMapOfTotalRatio = new LinkedHashMap<>();
        List<Double> listOfValue = valuteRepo.getConcreteValue(charCode);


        for (String str : charCodesOfOtherValutes) {
            if (!str.equals(charCode)) {
                List<Double> listOfRatio = new ArrayList<>();

                int i = 0;
                List<Double> concreteValue = valuteRepo.getConcreteValue(str);

                for (Double otherValue : concreteValue) {

                    listOfRatio.add(new BigDecimal((listOfValue.get(i))
                            / (otherValue))
                            .setScale(4, RoundingMode.UP).doubleValue());

                    i++;
                }
                resultMapOfTotalRatio.put(str, listOfRatio);
                log.debug("calculate ratio and put to map complete");
            }
        }
        return resultMapOfTotalRatio;
    }

    // получение выборки тех валют , которые нужны
    public static List<Valute> getSelection(Map<String, Valute> map, String... args) {
        List<Valute> list = new ArrayList<>();
        for (String arg : args) {
            list.add(map.get(arg));
        }
        return list;
    }

    // вспомогательный метод
    public String[] getArrayOfCharCodes() {
        return new String[]{"USD", "EUR", "CNY", "JPY"};
    }
}
