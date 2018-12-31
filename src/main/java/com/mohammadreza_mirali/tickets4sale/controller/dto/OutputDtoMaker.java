package com.mohammadreza_mirali.tickets4sale.controller.dto;

import com.mohammadreza_mirali.tickets4sale.domain.ticket.SellStatistic;
import com.mohammadreza_mirali.tickets4sale.domain.ticket.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This is a class for converting data from our Model to DTO.
 */
@Service
@Validated
public class OutputDtoMaker {
    final private TicketService ticketService;

    @Value("${DEFAULT_SHOW_CSV_PATH}")
    private String defaultShowCsvPath;
    @Autowired
    public OutputDtoMaker(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    /**
     * This is for converting entities to DTO
     * @param filePath is the path of CSV file
     * @param queryDate
     * @param showDate
     * @return a list of DTO objects, they will be in our JSON output
     * @throws IOException
     */
    public List<ResultOutputDto> makeOutputDto(String filePath,
                                               LocalDate queryDate,
                                               @NotNull LocalDate showDate) throws IOException {
        /**
         * When CSV file path is null, the default path from application.property file will be used.
         * When Query date is null, the default date is today.
         */
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
