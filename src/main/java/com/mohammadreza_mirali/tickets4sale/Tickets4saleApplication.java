package com.mohammadreza_mirali.tickets4sale;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mohammadreza_mirali.tickets4sale.domain.output.OutputDtoMaker;
import com.mohammadreza_mirali.tickets4sale.domain.output.ResultOutputDto;
import com.mohammadreza_mirali.tickets4sale.domain.show.ShowEntity;
import com.mohammadreza_mirali.tickets4sale.domain.show.ShowService;
import com.mohammadreza_mirali.tickets4sale.domain.ticket.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;


@SpringBootApplication
public class Tickets4saleApplication implements CommandLineRunner {

	@Autowired
	OutputDtoMaker outputDtoMaker;
private static Scanner scanner = new Scanner( System.in );

	public static void main(String[] args) {
		SpringApplication.run(Tickets4saleApplication.class, args);

	}

	@Override
	public void run(String... strings) throws Exception {
		if(strings.length==0)
			return;
		List<ResultOutputDto> resultOutputDtoList= outputDtoMaker.makeOutputDto(strings[0],LocalDate.parse(strings[1]),LocalDate.parse(strings[2]));
		ObjectMapper objectMapper = new ObjectMapper();
		String output = objectMapper.writeValueAsString(resultOutputDtoList);
		output = "{\"inventory\": ["+output+"]}";
		System.out.println(output);
//		ticketService.findStatusesFromCsv(strings[0],LocalDate.parse(strings[1]),LocalDate.parse(strings[2]));
	}

}
