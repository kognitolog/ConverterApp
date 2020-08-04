package ru.kozlovdev.ConverterApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.kozlovdev.ConverterApp.domain.Currency;
import ru.kozlovdev.ConverterApp.domain.JournalRecord;
import ru.kozlovdev.ConverterApp.service.CurrencyService;
import ru.kozlovdev.ConverterApp.service.JournalRecordService;

import java.util.*;

@Controller
public class MainController {

    private RestTemplate restTemplate;

    @Autowired
    private CurrencyService currencyService;
    @Autowired
    private JournalRecordService journalRecordService;

    @Value("${request.url}")
    private String url;

    @Autowired
    public MainController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @GetMapping(value = "/journal")
    public String journal(Model model) {
        model.addAttribute("journal", journalRecordService.findAll());
        return "journal";
    }

    @GetMapping(value = "/home")
    public String home(Model model) {
        model.addAttribute("currencies", currencyService.findAll());
        return "home";
    }

    @GetMapping(value = "/")
    public String updateCurrencies(Model model) {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_XML));
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        ResponseEntity<Currency[]> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                Currency[].class
        );

        List<Currency> currencyList = Arrays.asList(Objects.requireNonNull(response.getBody()));
        for (Currency current : currencyList) {
            if (current.getId() != null) {
                currencyService.save(current);
            }
        }
        Currency ruble = new Currency("R01", 643, "RUB", 1, "Российский рубль", "1.0");
        currencyService.save(ruble);

        model.addAttribute("currencies", currencyService.findAll());

        return "home";
    }

    @PostMapping(value = "convert")
    public String getResult(@RequestParam("currency") String currency,
                            @RequestParam("newcurrency") String newcurrency,
                            @RequestParam("count") String count, Model model
    ) {

        try {
            Currency currencyFrom = currencyService.findByNumCode(Integer.parseInt(currency)).get(0);
            Currency currencyTo = currencyService.findByNumCode(Integer.parseInt(newcurrency)).get(0);
            double result = 0;
            double valueTo = Double.parseDouble(currencyTo.getValue().replaceAll(",", "."));
            double valueFrom = Double.parseDouble(currencyFrom.getValue().replaceAll(",", "."));
            if (currencyTo.getId().equals("R01")) {
                result = Double.parseDouble(count) / currencyFrom.getNominal() * valueFrom;
            } else if (currencyFrom.getId().equals("R01")) {
                result = Double.parseDouble(count) * currencyTo.getNominal() / valueTo;
            }
            else {
                Double resultRub = Double.parseDouble(count) / currencyFrom.getNominal() * valueFrom;
                result = resultRub * currencyTo.getNominal() / valueTo;
            }

            JournalRecord record = new JournalRecord(
                    currencyFrom.getCharCode(),
                    currencyTo.getCharCode(),
                    Double.parseDouble(count),
                    result,
                    new Date());
            journalRecordService.save(record);

            String message = "Результат: " + count + " " + currencyFrom.getName() + " = " + result + " " + currencyTo.getName();

            model.addAttribute("result", message);
        } catch (java.lang.IndexOutOfBoundsException e) {
            model.addAttribute("result", "Не выбрана валюта для конвертации");
        } finally {
            model.addAttribute("currencies", currencyService.findAll());
        }
        return "home";
    }

    @PostMapping(value = "filter")
    public String filter(@RequestParam String filter, Model model) {
        Iterable<JournalRecord> journalRecords;

        if (filter != null && !filter.isEmpty()) {
            journalRecords = journalRecordService.findByCurrencyFrom(filter);
        } else {
            journalRecords = journalRecordService.findAll();
        }

        model.addAttribute("messages", journalRecords);

        return "journal";
    }


}
