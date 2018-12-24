package com.mohammadreza_mirali.tickets4sale.domain.show;

import com.mohammadreza_mirali.tickets4sale.domain.CsvFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mmirali on 08/12/2018.
 */
@Component
public class ShowService {

    private final ShowRepository showRepository;
    private final CsvFileReader csvFileReader;

    @Autowired
    public ShowService(ShowRepository showRepository, CsvFileReader csvFileReader) {
        this.showRepository = showRepository;
        this.csvFileReader = csvFileReader;
    }


public void saveAllFromCsv(String filePath) throws IOException {

    List<String[]> inventoryData = csvFileReader.readCsvFile(filePath);
    List<ShowEntity> showEntities = new ArrayList<>();
    inventoryData.forEach(strings -> {
        String title = strings[0];
        if(title.length()<1)
            return;
        LocalDate localDate = LocalDate.parse(strings[1]);
        GenreEnum genreEnum = GenreEnum.valueOf(strings[2].toUpperCase());
        ShowEntity showEntity = showRepository.findByTitleAndStartDateAndGenreEnum(title,localDate,genreEnum);
        if(showEntity == null)
             showEntity = new ShowEntity();
        showEntity.setTitle(strings[0]).setStartDate(LocalDate.parse(strings[1])).setGenreEnum(genreEnum);
        showEntities.add(showEntity);
    });
    showRepository.saveAll(showEntities);
}

}
