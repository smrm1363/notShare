package com.mohammadreza_mirali.tickets4sale.domain.ticket;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TicketServiceTest {
    @Autowired
    TicketService ticketService;
    @Autowired
    ApplicationContext ctx;
    @Test
    public void sellTicket() throws Exception {

        TicketEntity ticketEntity = new TicketEntity();
        ticketEntity.setPerformancetDate(LocalDate.of(2019,01,20));
//        ticketEntity.setPerformancetDate(null);

//        ticketEntity.setSoldDate(LocalDate.of(2019,01,1));
        ticketService.sellTicket(ticketEntity);
    }

    @Test
    public void findStatusesFromCsv() throws Exception
    {
        CommandLineRunner runner = ctx.getBean(CommandLineRunner.class);
        runner.run ( "E:\\work\\projects\\Netherlands\\Otravo\\shows.csv", "2018-04-25", "2018-15-26");

        ticketService.findStatusesFromCsv("E:\\work\\projects\\Netherlands\\Otravo\\shows.csv",
                LocalDate.of(2018,4,25),LocalDate.of(2018,5,26));
        ticketService.findStatusesFromCsv("E:\\work\\projects\\Netherlands\\Otravo\\shows.csv",
                "2018-04-25","2018-15-26");
    }

}