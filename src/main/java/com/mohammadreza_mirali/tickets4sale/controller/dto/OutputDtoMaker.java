package com.mohammadreza_mirali.tickets4sale.controller.dto;

import com.mohammadreza_mirali.tickets4sale.domain.ticket.SellStatistic;
import com.mohammadreza_mirali.tickets4sale.domain.ticket.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OutputDtoMaker {
    final private TicketService ticketService;

    @Value("${DEFAULT_SHOW_CSV_PATH}")
    private String defaultShowCsvPath;
    @Autowired
    public OutputDtoMaker(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public List<ResultOutputDto> makeOutputDto(@Pattern(regexp = "([^\\s]+(\\.(?i)(csv))$)")String filePath,
                                               LocalDate queryDate,
                                               @NotNull LocalDate showDate) throws IOException {

        List<SellStatistic> sellStatisticList = ticketService.
                findStatusesFromCsv(filePath!=null?filePath:defaultShowCsvPath,
                queryDate!=null?queryDate:LocalDate.now(),showDate);
        List<ResultOutputDto> resultList = new ArrayList<>();
        sellStatisticList.stream()
                .collect(Collectors.groupingBy(o -> o.getTicketEntity().getShowEntity().getGenreEnum()))
                .forEach((genreEnum, sellStatistics) ->
                {
                    ResultOutputDto resultOutputDto = new ResultOutputDto();
                    resultOutputDto.setGenre(genreEnum.toString());
                    resultOutputDto.setShows(
                            sellStatistics.stream().map(sellStatistic ->
                            {
                                ShowOutPutDto showOutPutDto = new ShowOutPutDto();
                                showOutPutDto.setTitle(sellStatistic.getTicketEntity().getShowEntity().getTitle());
                                showOutPutDto.setStatus(sellStatistic.getTicketEntity().getShowEntity().getShowStateEnum());
                                showOutPutDto.setTicketsLeft(sellStatistic.getTotalSoldTicket());
                                showOutPutDto.setTicketsAvailable(sellStatistic.getTotalAvailableTicket());
                                showOutPutDto.setPrice(sellStatistic.getTicketEntity().getPrice());
                                return showOutPutDto;
                            }).collect(Collectors.toList())
                    );
                    resultList.add(resultOutputDto);
                });



        return resultList;
    }


}
