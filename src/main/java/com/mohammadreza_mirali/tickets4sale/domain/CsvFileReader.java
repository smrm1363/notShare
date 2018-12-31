package com.mohammadreza_mirali.tickets4sale.domain;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Pattern;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * Class for reading CSV file
 */
@Validated
@Component
public class CsvFileReader {
    /**
     *
     * @param filePath should be valid path for CSV file
     * @return a list of Strings as a result
     * @throws IOException when file does not exist
     */
    public List<String[]> readCsvFile(@Pattern(regexp = "([^\\s]+(\\.(?i)(csv))$)",message = "{FILE_PATH_IS_NOT_VALID}")String filePath) throws IOException {
        // Create an object of file reader
        // class with CSV file as a parameter.
        FileReader filereader = new FileReader(filePath);
        CSVReader csvReader = new CSVReaderBuilder(filereader).build();
        return csvReader.readAll();

    }
}
