package com.telus.dl.profilemanagement.service;

import com.telus.dl.profilemanagement.document.DowJonesIndex;
import com.telus.dl.profilemanagement.repository.DowJonesIndexRepository;
import com.telus.dl.profilemanagement.util.CsvUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

@Service
public class CsvFileService {
    private DowJonesIndexRepository repository;

    @Autowired
    public CsvFileService(DowJonesIndexRepository repository) {
        this.repository = repository;
    }

    public void store(InputStream in) {
        try {
            // Using ApacheCommons Csv Utils to parse CSV file
            List<DowJonesIndex> lstIndexes = CsvUtil.parseCsvFile(in);

            // Save customers to database
            repository.saveAll(lstIndexes);
        } catch (Exception e) {
            throw new RuntimeException("FAIL! -> message = " + e.getMessage(), e);
        }
    }
}
