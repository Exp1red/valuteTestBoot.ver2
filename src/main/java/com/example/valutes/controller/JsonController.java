package com.example.valutes.controller;

import com.example.valutes.jsonpojo.ForChartJsonComplex;
import com.example.valutes.jsonpojo.ForChartJsonOne;
import com.example.valutes.repos.ValuteRepo;
import com.example.valutes.util.ValuteHelper;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class JsonController {
    final ValuteRepo valuteRepo;
    final ValuteHelper valuteHelper;

    public JsonController(ValuteRepo valuteRepo, ValuteHelper valuteHelper) {
        this.valuteRepo = valuteRepo;
        this.valuteHelper = valuteHelper;
    }


    @GetMapping("/getDate")
    public List<String> getDate() {
        return valuteRepo.getSortedDate().stream()
                .map(x -> (new SimpleDateFormat("dd/MM/yy").format(x)))
                .collect(Collectors.toList());
    }

    @GetMapping("/getRatio/{charCode}")
    public Map<String, List<Double>> getRatioCurrentValuteToOthers(@PathVariable("charCode") String charCode) {

        return valuteHelper.getRatio(charCode, valuteHelper.getArrayOfCharCodes());
    }

    @CrossOrigin
    @GetMapping("/getFullDataForChart/{charCode}")
    public String getFullDataForChart(@PathVariable("charCode") String charCode) {

        Map<String, List<Double>> ratios = getRatioCurrentValuteToOthers(charCode);
        List<String> dates = getDate();


        ForChartJsonComplex forChartJson = new ForChartJsonComplex();
        forChartJson.setDate(dates);
        forChartJson.setRatios(ratios);

        Gson gson = new Gson();
        return gson.toJson(forChartJson); // работает , но я не умею парсить такой json в график

    }

    @CrossOrigin
    @GetMapping("/test/{charCode}")
    public String test(@PathVariable("charCode") String charCode) {

        List<ForChartJsonOne> resultList = new ArrayList<>();

        List<List<Double>> ratiosToOtherOfOneObj = new ArrayList<>();

        Map<String, List<Double>> ratios = getRatioCurrentValuteToOthers(charCode);
        for (Map.Entry<String, List<Double>> pair : ratios.entrySet()) {
            ratiosToOtherOfOneObj.add(pair.getValue());
        }


        List<String> dates = getDate();
        int i = 0;
        for (String date : dates) {

            resultList.add(new ForChartJsonOne(date,
                    ratiosToOtherOfOneObj.get(0).get(i),
                    ratiosToOtherOfOneObj.get(1).get(i),
                    ratiosToOtherOfOneObj.get(2).get(i)));
            i++;
        }


        return new Gson().toJson(resultList);

    }


}
