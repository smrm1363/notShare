package com.mohammadreza_mirali.tickets4sale.domain.ticket;

import com.mohammadreza_mirali.tickets4sale.domain.ApplicationException;
import com.mohammadreza_mirali.tickets4sale.domain.show.ShowService;
import com.mohammadreza_mirali.tickets4sale.domain.show.ShowStateEnum;
import com.mohammadreza_mirali.tickets4sale.domain.ticket.pricing.PriceStrategy;
import com.mohammadreza_mirali.tickets4sale.domain.ticket.pricing.PriceStrategyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;


@Service
@Validated
public class TicketService {
    private final PriceStrategyFactory priceStrategyFactory;
    private final ShowService showService;


    @Value("${BIG_HALL_TO_SMALL_HALL_CONDITION}")
    private Short bigHallToSmallHallCondition;
    @Value("${HOW_MANY_DAYS_BEFORE_SELLING_STARTS}")
    private Short howManyDaysBeforeSellingStarts;

    @Autowired
    public TicketService(PriceStrategyFactory priceStrategyFactory, ShowService showService) {
        this.priceStrategyFactory = priceStrategyFactory;
        this.showService = showService;

    }

    public TicketEntity sellTicket(@Valid TicketEntity ticketEntity) throws IOException, ApplicationException {
        PriceStrategy priceStrategy = priceStrategyFactory.getPriceStrategyInstance(ticketEntity.getPerformancetDate(),ticketEntity.getShowEntity());
        ticketEntity.setPrice(priceStrategy.calculatePrice(ticketEntity.getShowEntity()));
        ticketEntity.setHallEnum(findHall( ticketEntity));
        return ticketEntity;
     }

    public SellStatistic addSoldTicketsEachDay(SellStatistic sellStatistic) throws IOException, ApplicationException {
        sellTicket(sellStatistic.getTicketEntity());
        sellStatistic.setTotalSoldTicket(sellStatistic.getTicketEntity().getHallEnum().getSellPerDay()+sellStatistic.getTotalSoldTicket());
        return sellStatistic;
    }

    public List<SellStatistic> findStatusesFromCsv(String filePath, LocalDate queryDate, LocalDate showDate) throws IOException {
        showService.convertAllFromCsv(filePath);
        List<SellStatistic> sellStatisticList = new ArrayList<>();
        showService.findAllShowes().forEach(showEntity ->
        {
            LocalDate startSellingDate = showDate.minusDays(howManyDaysBeforeSellingStarts);
            Long daysToShowDate = DAYS.between(queryDate,showDate);
            TicketEntity ticketEntity = new TicketEntity();
            ticketEntity.setShowEntity(showEntity);
            ticketEntity.setPerformancetDate(showDate);
            SellStatistic sellStatistic = new SellStatistic();
            sellStatistic.setTicketEntity(ticketEntity);
            sellStatistic.setTotalSoldTicket(0);

            try {
                if(daysToShowDate<0 || showEntity.getEndDate().isBefore(showDate))
                {
                    sellStatistic.getTicketEntity().getShowEntity().setShowStateEnum(ShowStateEnum.IN_THE_PAST);
                }
                else if(daysToShowDate<=howManyDaysBeforeSellingStarts)
                {
                        while (queryDate.isAfter(startSellingDate)) {
                            addSoldTicketsEachDay(sellStatistic);
                            if(sellStatistic.getTotalSoldTicket()>=sellStatistic.getTotalAvailableTicket())
                                break;
                            startSellingDate = startSellingDate.plusDays(1);
                        }
                            if(sellStatistic.getTotalAvailableTicket()>0)
                                sellStatistic.getTicketEntity().getShowEntity().setShowStateEnum(ShowStateEnum.OPEN_FOR_SALE);
                            else
                                sellStatistic.getTicketEntity().getShowEntity().setShowStateEnum(ShowStateEnum.SOLD_OUT);
                            }
                else
                {
                    sellStatistic.getTicketEntity().getShowEntity().setShowStateEnum(ShowStateEnum.SALE_NOT_STARTED);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ApplicationException e) {
                e.printStackTrace();
            }

            sellStatisticList.add(sellStatistic);
        });
        return sellStatisticList;

    }
    public List<SellStatistic> findStatusesFromCsv(@Pattern(regexp = "([^\\s]+(\\.(?i)(csv))$)")String filePath,
                                                   @NotNull String queryDate,
                                                   @NotNull String showDate) throws IOException, ApplicationException {
        try {
            return findStatusesFromCsv(filePath, LocalDate.parse(queryDate), LocalDate.parse(showDate));
        }
        catch (DateTimeParseException  e)
        {
            throw new ApplicationException("The format of dates should be YYYY-MM-dd");
        }
    }
    private HallEnum findHall(TicketEntity ticketEntity)
    {
        if(DAYS.between(ticketEntity.getShowEntity().getStartDate(),ticketEntity.getPerformancetDate())>bigHallToSmallHallCondition)
            return HallEnum.SMALL;
        return HallEnum.BIG;
    }
}
