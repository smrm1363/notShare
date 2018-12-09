package com.mohammadreza_mirali.tickets4sale.domain.repository;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * Created by mmirali on 09/12/2018.
 */
@Component
public class CsvFileReader {
    public List<String[]> readCsvFile(String filePath) throws IOException {
        // Create an object of file reader
        // class with CSV file as a parameter.
        FileReader filereader = new FileReader(filePath);
        CSVReader csvReader = new CSVReaderBuilder(filereader).build();
        return csvReader.readAll();

    }
}
