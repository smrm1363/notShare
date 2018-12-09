package com.mohammadreza_mirali.tickets4sale;

import com.mohammadreza_mirali.tickets4sale.domain.Show;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Tickets4saleApplication {

	public static void main(String[] args) {
		SpringApplication.run(Tickets4saleApplication.class, args);
		Show show = new Show();
		show.setTitle("");
	}
}
