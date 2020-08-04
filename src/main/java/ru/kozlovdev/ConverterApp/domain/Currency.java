package ru.kozlovdev.ConverterApp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

@Entity
@XmlRootElement(name = "Valute")
public class Currency implements Serializable {

    @Id
    @JsonProperty("ID")
    private String id;
    @JsonProperty("NumCode")
    private Integer numCode;
    @JsonProperty("CharCode")
    private String charCode;
    @JsonProperty("Nominal")
    private Integer nominal;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Value")
    private String value;

    public Currency(String id, Integer numCode, String charCode, Integer nominal, String name, String value) {
        this.id = id;
        this.numCode = numCode;
        this.charCode = charCode;
        this.nominal = nominal;
        this.name = name;
        this.value = value;
    }

    public Currency() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getNumCode() {
        return numCode;
    }

    public void setNumCode(Integer numCode) {
        this.numCode = numCode;
    }

    public String getCharCode() {
        return charCode;
    }

    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }

    public Integer getNominal() {
        return nominal;
    }

    public void setNominal(Integer nominal) {
        this.nominal = nominal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}