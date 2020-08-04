package ru.kozlovdev.ConverterApp.repo;

import org.springframework.data.repository.CrudRepository;
import ru.kozlovdev.ConverterApp.domain.JournalRecord;
import java.util.List;

public interface JournalRecordRepo extends CrudRepository<JournalRecord, Long> {
    List<JournalRecord> findByCurrencyFrom(String code);

//    @Query("select * from journal_record where currencyFrom == :currencyFrom and currencyTo == :currencyTo")
//    List<JournalRecord>findByCurrencyFromOrCurrencyTo(@Param("currencyFrom") String currencyFrom, @Param("currencyTo") String currencyTo);

}
