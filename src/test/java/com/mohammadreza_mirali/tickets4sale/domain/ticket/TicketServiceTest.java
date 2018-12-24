package com.mohammadreza_mirali.tickets4sale.domain.ticket;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TicketServiceTest {
    @Autowired
    TicketService ticketService;
    @Test
    public void sellTicket() throws Exception {

        TicketEntity ticketEntity = new TicketEntity();
        ticketEntity.setTicketDate(LocalDate.of(2019,01,20));
        ticketEntity.setSoldDate(LocalDate.of(2019,01,1));
        ticketService.sellTicket(ticketEntity);
    }

}