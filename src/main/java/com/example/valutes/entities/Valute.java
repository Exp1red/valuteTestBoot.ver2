package com.example.valutes.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(schema = "json", name = "valute" )
public class Valute  {


    @Id
    @GeneratedValue
    @JsonIgnore
    private long id;

    @JsonProperty("ID")
    private String stringId;
    @JsonProperty("NumCode")
    private int numCode;
    @JsonProperty("CharCode")
    private String charCode;
    @JsonProperty("Nominal")
    private int nominal;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Value")
    private double value;
    @JsonProperty("Previous")
    private double previous;

    @JsonIgnore
    private Date date;


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date ;
    }

    public long getId() {
        return id;    }

    public void setId(long id) {
        this.id = id;    }

    public String getStringId() {
        return stringId;    }


    public void setStringId(String stringId) {
        this.stringId = stringId;    }

    public int getNumCode() {
        return numCode;    }

    public void setNumCode(int numCode) {
        this.numCode = numCode;    }

    public String getCharCode() {
        return charCode;    }

    public void setCharCode(String charCode) {
        this.charCode = charCode;    }

    public int getNominal() {
        return nominal;    }

    public void setNominal(int nominal) {
        this.nominal = nominal;    }

    public String getName() {
        return name;    }

    public void setName(String name) {
        this.name = name;    }

    public double getValue() {
        return value;    }

    public void setValue(double value) {
        this.value = value;    }

    public double getPrevious() {
        return previous;    }

    public void setPrevious(double previous) {
        this.previous = previous;    }
}


