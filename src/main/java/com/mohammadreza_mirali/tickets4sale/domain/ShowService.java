package com.mohammadreza_mirali.tickets4sale.domain;

import com.mohammadreza_mirali.tickets4sale.domain.repository.CsvFileReader;
import com.mohammadreza_mirali.tickets4sale.domain.repository.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by mmirali on 08/12/2018.
 */
@Component
public class ShowService {
    @Autowired
    ShowRepository showRepository;
    @Autowired
    CsvFileReader csvFileReader;
public void create(Show show)
{
    showRepository.create(show);
}

public void createAllFromCsv(String filePath) throws IOException {

    List<String[]> inventoryData = csvFileReader.readCsvFile(filePath);
    inventoryData.forEach(strings -> {
        String title = strings[0];
        LocalDate localDate = LocalDate.parse(strings[1]);
        Show show = new Show().setTitle(strings[0]).setStartDate(LocalDate.parse(strings[1])).setGenre(GenreEnum.valueOf(strings[3].toUpperCase()));
        showRepository.create(show);
    });
}

}
