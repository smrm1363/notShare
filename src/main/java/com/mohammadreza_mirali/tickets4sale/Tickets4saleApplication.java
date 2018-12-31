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

/**
 * The starter class of Spring Boot application
 */
@SpringBootApplication
public class Tickets4saleApplication implements CommandLineRunner {

    private static Scanner scanner = new Scanner(System.in);
    @Autowired
    OutputDtoMaker outputDtoMaker;

    public static void main(String[] args) {
        SpringApplication.run(Tickets4saleApplication.class, args);

    }

    /**
     * Run application as a CLI tools if inputted arguments are not empty.
     * @param strings arguments of the program, it could has no parameter or 3 parameters (the path of CSV file, the query date, and the show date)
     * @throws Exception
     */
    @Override
    public void run(String... strings) throws Exception {
        /**
         * If the arguments are not exist, only REST service works
         */
        if (strings.length == 0)
            return;

        /**
         * If we want to run CLI tools
         */
        List<ResultOutputDto> resultOutputDtoList = outputDtoMaker.makeOutputDto(strings[0], LocalDate.parse(strings[1]), LocalDate.parse(strings[2]));
        resultOutputDtoList.forEach(resultOutputDto ->
        {
            /**
             * Converting the DTO object to an other DTO without Price filed
             */
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
