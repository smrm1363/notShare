package com.mohammadreza_mirali.tickets4sale.controller.dto;

import com.mohammadreza_mirali.tickets4sale.domain.show.GenreEnum;
import com.mohammadreza_mirali.tickets4sale.domain.show.ShowEntity;
import com.mohammadreza_mirali.tickets4sale.domain.show.ShowStateEnum;
import com.mohammadreza_mirali.tickets4sale.domain.ticket.HallEnum;
import com.mohammadreza_mirali.tickets4sale.domain.ticket.SellStatistic;
import com.mohammadreza_mirali.tickets4sale.domain.ticket.TicketEntity;
import com.mohammadreza_mirali.tickets4sale.domain.ticket.TicketService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by mmirali on 31/12/2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class OutputDtoMakerTest {
    @Mock
    TicketService ticketService;
    @Test
    public void makeOutputDto() throws Exception {
        OutputDtoMaker outputDtoMaker = new OutputDtoMaker(ticketService);
        when(ticketService.findStatusesFromCsv(any(),any(),any())).thenReturn(findSellStatisticList());
        List<ResultOutputDto> resultOutputDtos = outputDtoMaker.makeOutputDto("",null,null);
        assertTrue(resultOutputDtos.get(0).getGenre().equals(GenreEnum.COMEDY.toString()));
    }

    private List<SellStatistic> findSellStatisticList()
    {
        List<SellStatistic> sellStatisticList = new ArrayList<>();
        SellStatistic sellStatistic = new SellStatistic();
        TicketEntity ticketEntity = new TicketEntity();
        ticketEntity.setHallEnum(HallEnum.BIG);
        ticketEntity.setPerformanceDate(LocalDate.now());
        ticketEntity.setPrice(15);
        ticketEntity.setShowEntity(new ShowEntity().setGenreEnum(GenreEnum.COMEDY)
                .setStartDate(LocalDate.now().minusDays(20)).setTitle("Test show").setShowStateEnum(ShowStateEnum.OPEN_FOR_SALE));
        sellStatistic.setTicketEntity(ticketEntity);

        sellStatisticList.add(sellStatistic);
        return sellStatisticList;
    }

}