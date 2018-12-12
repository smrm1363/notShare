package com.mohammadreza_mirali.tickets4sale;

import com.mohammadreza_mirali.tickets4sale.domain.show.ShowEntity;
import com.mohammadreza_mirali.tickets4sale.domain.show.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Tickets4saleApplication implements CommandLineRunner {

	@Autowired
	ShowService showService;

	public static void main(String[] args) {
		SpringApplication.run(Tickets4saleApplication.class, args);
		ShowEntity showEntity = new ShowEntity();

		showEntity.setTitle("");
	}

	@Override
	public void run(String... strings) throws Exception {

		showService.saveAllFromCsv("E:\\projects\\myself\\Netherland\\Otravo\\shows.csv");
	}
}
