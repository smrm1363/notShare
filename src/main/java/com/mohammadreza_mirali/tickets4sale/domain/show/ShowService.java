package com.mohammadreza_mirali.tickets4sale.domain.show;

import com.mohammadreza_mirali.tickets4sale.domain.CsvFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mmirali on 08/12/2018.
 */
@Service
public class ShowService {


    private final CsvFileReader csvFileReader;
    private final ShowInventory showInventory;

    @Autowired
    public ShowService(CsvFileReader csvFileReader, ShowInventory showInventory) {
        this.csvFileReader = csvFileReader;
        this.showInventory = showInventory;
    }


public void convertAllFromCsv(String filePath) throws IOException {

    List<String[]> inventoryData = csvFileReader.readCsvFile(filePath);
    List<ShowEntity> showEntities = new ArrayList<>();
    inventoryData.forEach(strings -> {
        String title = strings[0];
        if(title.length()<1)
            return;
        GenreEnum genreEnum = GenreEnum.valueOf(strings[2].toUpperCase());
        ShowEntity showEntity = new ShowEntity();
        showEntity.setTitle(strings[0]).setStartDate(LocalDate.parse(strings[1])).setGenreEnum(genreEnum);
        showEntities.add(showEntity);
    });
    showInventory.setShowEntityList(showEntities);

}
public List<ShowEntity> findAllShows()
{
    return showInventory.getShowEntityList();
}

}
