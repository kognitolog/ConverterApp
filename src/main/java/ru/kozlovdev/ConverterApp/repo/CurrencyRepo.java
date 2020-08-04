package ru.kozlovdev.ConverterApp.repo;

import org.springframework.data.repository.CrudRepository;
import ru.kozlovdev.ConverterApp.domain.Currency;

import java.util.*;

public interface CurrencyRepo extends CrudRepository<Currency, Long> {
    List<Currency> findByNumCode(Integer numCode);
}
