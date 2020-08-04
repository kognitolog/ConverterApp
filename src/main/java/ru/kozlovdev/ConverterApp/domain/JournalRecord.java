package ru.kozlovdev.ConverterApp.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
public class JournalRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String currencyFrom;
    private String currencyTo;
    private Double amount;
    private Double result;
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public JournalRecord() {
    }

    public String getCurrencyFrom() {
        return currencyFrom;
    }

    public void setCurrencyFrom(String currencyFrom) {
        this.currencyFrom = currencyFrom;
    }

    public String getCurrencyTo() {
        return currencyTo;
    }

    public void setCurrencyTo(String currencyTo) {
        this.currencyTo = currencyTo;
    }

    public JournalRecord(String currencyFrom, String currencyTo, Double amount, Double result, Date date) {
        this.currencyFrom = currencyFrom;
        this.currencyTo = currencyTo;
        this.amount = amount;
        this.result = result;
        this.date = date;
    }
}
