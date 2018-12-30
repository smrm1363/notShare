package com.mohammadreza_mirali.tickets4sale.domain.ticket;

import com.mohammadreza_mirali.tickets4sale.domain.ApplicationException;
import com.mohammadreza_mirali.tickets4sale.domain.show.GenreEnum;
import com.mohammadreza_mirali.tickets4sale.domain.show.ShowEntity;
import com.mohammadreza_mirali.tickets4sale.domain.show.ShowService;
import com.mohammadreza_mirali.tickets4sale.domain.ticket.pricing.PriceStrategyFactory;
import com.mohammadreza_mirali.tickets4sale.util.PropertiesLoader;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TicketServiceTest {
    @Spy
    PriceStrategyFactory priceStrategyFactory;
    @Mock
    ShowService showService;


    @Test
    public void sellTicket() throws Exception {
        TicketService ticketService = new TicketService(priceStrategyFactory,null);
        TicketService tickServiceSpy = spy(ticketService);
        doReturn(HallEnum.BIG).when(tickServiceSpy).findHall(any());
        TicketEntity ticketEntity = new TicketEntity();
        ShowEntity showEntity =  new ShowEntity().setGenreEnum(GenreEnum.COMEDY).setStartDate(LocalDate.of(2019,01,01)).setTitle("Test show");
        ticketEntity.setShowEntity(showEntity);
        ticketEntity.setPerformanceDate(LocalDate.of(2019,01,20));
        tickServiceSpy.sellTicket( ticketEntity);
        assertTrue(ticketEntity.getHallEnum().equals(HallEnum.BIG));
        assertTrue(ticketEntity.getPrice().equals(
                Integer.valueOf(PropertiesLoader.loadProperties("application.properties").getProperty("COMEDY"))));
        ticketEntity.setPerformanceDate(LocalDate.of(2019,03,25));
        tickServiceSpy.sellTicket(ticketEntity);
        int ratio = Integer.valueOf(PropertiesLoader.loadProperties("application.properties").getProperty("80PERCENT"));
        assertTrue(ticketEntity.getPrice()==(
                Integer.valueOf(PropertiesLoader.loadProperties("application.properties").getProperty("COMEDY"))
                *.01*ratio));
    }

    @Test
    public void addSoldTicketsEachDay() throws IOException, ApplicationException {
        TicketService ticketService = new TicketService(priceStrategyFactory,null);
        TicketService tickServiceSpy = spy(ticketService);
        SellStatistic sellStatistic = new SellStatistic();
        TicketEntity ticketEntity = new TicketEntity();
        ShowEntity showEntity =  new ShowEntity().setGenreEnum(GenreEnum.COMEDY).setStartDate(LocalDate.of(2019,01,01)).setTitle("Test show");
        ticketEntity.setShowEntity(showEntity);
        ticketEntity.setPerformanceDate(LocalDate.of(2019,01,20));
        ticketEntity.setHallEnum(HallEnum.SMALL);
        doReturn(ticketEntity).when(tickServiceSpy).sellTicket(ticketEntity);
        sellStatistic.setTicketEntity(ticketEntity);
        tickServiceSpy.addSoldTicketsEachDay(sellStatistic);
        assertTrue(sellStatistic.getTotalSoldTicket().equals(5));
        tickServiceSpy.addSoldTicketsEachDay(sellStatistic);
        assertTrue(sellStatistic.getTotalSoldTicket().equals(10));

    }
    @Test
    public void findStatusesFromCsv() throws Exception
    {
        TicketService ticketService = new TicketService(priceStrategyFactory,showService);
        TicketService tickServiceSpy = spy(ticketService);
        when(showService.findAllShows()).thenReturn(findAllShows());
        doNothing().when(showService).convertAllFromCsv(any());
        tickServiceSpy.setHowManyDaysBeforeSellingStarts((short) 25);
        List<SellStatistic> sellStatisticList = tickServiceSpy.findStatusesFromCsv("",LocalDate.of(2019,01,20)
                ,LocalDate.of(2019,02,21));
        assertTrue(sellStatisticList.size()==3);
//        CommandLineRunner runner = ctx.getBean(CommandLineRunner.class);
//        runner.run ( "E:\\work\\projects\\Netherlands\\Otravo\\shows.csv", "2018-04-25", "2018-15-26");
//
//        ticketService.findStatusesFromCsv("E:\\work\\projects\\Netherlands\\Otravo\\shows.csv",
//                LocalDate.of(2018,4,25),LocalDate.of(2018,5,26));
//        ticketService.findStatusesFromCsv("E:\\work\\projects\\Netherlands\\Otravo\\shows.csv",
//                LocalDate.parse("2018-04-25"),LocalDate.parse("2018-15-26"));


    }
    private List<ShowEntity> findAllShows()
    {
        List<ShowEntity> showEntityList = new ArrayList<>();
        showEntityList.add(new ShowEntity()
                .setGenreEnum(GenreEnum.COMEDY).setStartDate(LocalDate.of(2019,01,20)).setTitle("First show"));
        showEntityList.add(new ShowEntity()
                .setGenreEnum(GenreEnum.DRAMA).setStartDate(LocalDate.of(2019,02,20)).setTitle("Second show"));
        showEntityList.add(new ShowEntity()
                .setGenreEnum(GenreEnum.COMEDY).setStartDate(LocalDate.of(2019,03,20)).setTitle("Third show"));
        return showEntityList;
    }

}