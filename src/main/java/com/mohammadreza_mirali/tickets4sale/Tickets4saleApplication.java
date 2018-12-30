package com.mohammadreza_mirali.tickets4sale;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mohammadreza_mirali.tickets4sale.controller.dto.OutputDtoMaker;
import com.mohammadreza_mirali.tickets4sale.controller.dto.ResultOutputDto;
import com.mohammadreza_mirali.tickets4sale.controller.dto.ShowOutputDtoConsole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


@SpringBootApplication
public class Tickets4saleApplication implements CommandLineRunner {

    private static Scanner scanner = new Scanner(System.in);
    @Autowired
    OutputDtoMaker outputDtoMaker;

    public static void main(String[] args) {
        SpringApplication.run(Tickets4saleApplication.class, args);

    }

    @Override
    public void run(String... strings) throws Exception {
        if (strings.length == 0)
            return;

        List<ResultOutputDto> resultOutputDtoList = outputDtoMaker.makeOutputDto(strings[0], LocalDate.parse(strings[1]), LocalDate.parse(strings[2]));
        resultOutputDtoList.forEach(resultOutputDto ->
        {
            List<ShowOutputDtoConsole> showOutputDtoConsoles = new ArrayList<>();
            resultOutputDto.getShows().forEach(showOutputDto ->
            {
                ShowOutputDtoConsole showOutputDtoConsole = new ShowOutputDtoConsole();
                showOutputDtoConsole.setStatus(showOutputDto.getStatus());
                showOutputDtoConsole.setTicketsAvailable(showOutputDto.getTicketsAvailable());
                showOutputDtoConsole.setTicketsLeft(showOutputDto.getTicketsLeft());
                showOutputDtoConsole.setTitle(showOutputDto.getTitle());
                showOutputDtoConsoles.add(showOutputDtoConsole);
            });
            resultOutputDto.setShows(showOutputDtoConsoles);
        });
        ObjectMapper objectMapper = new ObjectMapper();
        String output = objectMapper.writeValueAsString(resultOutputDtoList);
        output = "{\"inventory\": [" + output + "]}";
        System.out.println(output);
    }

}
