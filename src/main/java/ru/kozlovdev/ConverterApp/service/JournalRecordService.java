package ru.kozlovdev.ConverterApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kozlovdev.ConverterApp.domain.JournalRecord;
import ru.kozlovdev.ConverterApp.repo.JournalRecordRepo;

import java.util.ArrayList;
import java.util.List;

@Service
public class JournalRecordService {

    @Autowired
    private JournalRecordRepo journalRecordRepo;

    public List<JournalRecord> findAll() {
        List<JournalRecord> journal = new ArrayList<>();
        journalRecordRepo.findAll().forEach(journal::add);
        return journal;
    }

    public JournalRecord save(JournalRecord journalRecord) {
        journalRecordRepo.save(journalRecord);
        return journalRecord;
    }

    public List<JournalRecord> findByCurrencyFrom(String code) {
        return journalRecordRepo.findByCurrencyFrom(code);
    }

}
