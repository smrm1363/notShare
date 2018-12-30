package com.mohammadreza_mirali.tickets4sale.domain.show;

import com.mohammadreza_mirali.tickets4sale.domain.CsvFileReader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ShowServiceTest {


    @Mock
    CsvFileReader csvFileReader;
    @Spy
    ShowInventory showInventory;
    @Test
    public void convertAllFromCsv() throws Exception {
        List<String[]> readedFromFile = readedFromFile();
        ShowService showService = new ShowService(csvFileReader,showInventory);
        when(csvFileReader.readCsvFile(any())).thenReturn(readedFromFile);
        showService.convertAllFromCsv("");
        assertTrue(showInventory.getShowEntityList().size()==3);
        assertTrue(showInventory.getShowEntityList().get(0).getTitle().equals(readedFromFile.get(0)[0]));

    }

    private List<String[]> readedFromFile()
    {
        List<String[]> readedFromFile = new ArrayList<>();
        readedFromFile.add(new String[]{"ANNIE JR","2018-02-11","MUSICAL"});
        readedFromFile.add(new String[]{"Loooo","2019-02-11","COMEDY"});
        readedFromFile.add(new String[]{"Winter","2018-10-11","MUSICAL"});
        return readedFromFile;
    }



}