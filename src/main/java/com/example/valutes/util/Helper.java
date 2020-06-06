package com.example.valutes.util;

import com.example.valutes.entities.Valute;
import com.example.valutes.jsonpojo.Json;
import com.example.valutes.repos.ValuteRepo;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Helper {

    public static void recursion(int countOfDays, ValuteRepo valuteRepo, String originalUrl) {

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

                recursion(countOfDays + 1, valuteRepo, "https:" + json.getPreviousUrl());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static List<Valute> getSelection(Map<String, Valute> map, String... args) {
        List<Valute> list = new ArrayList<>();
        for (String arg : args) {
            list.add(map.get(arg));
        }
        return list;
    }
}
