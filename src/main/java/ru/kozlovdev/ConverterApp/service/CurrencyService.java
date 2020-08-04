package ru.kozlovdev.ConverterApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kozlovdev.ConverterApp.domain.Currency;
import ru.kozlovdev.ConverterApp.repo.CurrencyRepo;

import java.util.ArrayList;
import java.util.List;

@Service
public class CurrencyService {

    @Autowired
    private CurrencyRepo currencyRepo;

    public List<Currency> findAll() {
        List<Currency> currencyList = new ArrayList<>();
        currencyRepo.findAll().forEach(currencyList::add);
        return currencyList;
    }

    public Currency save(Currency currency) {
        currencyRepo.save(currency);
        return currency;
    }

    public List<Currency> findByNumCode(Integer numCode) {
        return currencyRepo.findByNumCode(numCode);
    }
}
