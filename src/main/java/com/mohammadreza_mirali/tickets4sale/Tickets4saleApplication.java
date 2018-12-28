package com.mohammadreza_mirali.tickets4sale;

import com.mohammadreza_mirali.tickets4sale.domain.show.ShowEntity;
import com.mohammadreza_mirali.tickets4sale.domain.show.ShowService;
import com.mohammadreza_mirali.tickets4sale.domain.ticket.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.Scanner;


@SpringBootApplication
public class Tickets4saleApplication implements CommandLineRunner {

	@Autowired
	TicketService ticketService;
private static Scanner scanner = new Scanner( System.in );

	public static void main(String[] args) {
		SpringApplication.run(Tickets4saleApplication.class, args);

	}

	@Override
	public void run(String... strings) throws Exception {
		if(strings.length==0)
			return;
		ticketService.findStatusesFromCsv(strings[0],strings[1],strings[2]);
	}

}
