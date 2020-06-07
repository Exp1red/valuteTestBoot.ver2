package com.example.valutes.jsonpojo;

import com.example.valutes.entities.Valute;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.Map;


public class IncomeJsonAPI {

    @JsonProperty("Date")
    private Date date;

    @JsonProperty("PreviousDate")
    private Date previousDate;

    @JsonProperty("PreviousURL")
    private String previousUrl;

    @JsonProperty("Timestamp")
    private Date timestamp;

    @JsonProperty("Valute")
    private Map<String , Valute> valutes;


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getPreviousDate() {
        return previousDate;
    }

    public void setPreviousDate(Date previousDate) {
        this.previousDate = previousDate;
    }

    public String getPreviousUrl() {
        return previousUrl;
    }

    public void setPreviousUrl(String previousUrl) {
        this.previousUrl = previousUrl;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Map<String, Valute> getValutes() {
        return valutes;
    }

    public void setValutes(Map<String, Valute> valutes) {
        this.valutes = valutes;
    }
}
