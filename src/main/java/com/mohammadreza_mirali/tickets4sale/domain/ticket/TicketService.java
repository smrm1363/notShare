package com.mohammadreza_mirali.tickets4sale.domain.ticket;

import com.mohammadreza_mirali.tickets4sale.domain.show.ShowService;
import com.mohammadreza_mirali.tickets4sale.domain.show.ShowStateEnum;
import com.mohammadreza_mirali.tickets4sale.domain.ticket.pricing.PriceStrategy;
import com.mohammadreza_mirali.tickets4sale.domain.ticket.pricing.PriceStrategyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * This class is the responsible for all business logics related to ticket
 */
@Service
@Validated
public class TicketService {
    private final PriceStrategyFactory priceStrategyFactory;
    private final ShowService showService;



    private Short bigHallToSmallHallCondition;
    @Value("${BIG_HALL_TO_SMALL_HALL_CONDITION}")
    public void setBigHallToSmallHallCondition(Short bigHallToSmallHallCondition)
    {
        this.bigHallToSmallHallCondition = bigHallToSmallHallCondition;
    }

    private Short howManyDaysBeforeSellingStarts;
    @Value("${HOW_MANY_DAYS_BEFORE_SELLING_STARTS}")
    public void setHowManyDaysBeforeSellingStarts(Short howManyDaysBeforeSellingStarts)
    {
        this.howManyDaysBeforeSellingStarts = howManyDaysBeforeSellingStarts;
    }

    @Autowired
    public TicketService(PriceStrategyFactory priceStrategyFactory, ShowService showService) {
        this.priceStrategyFactory = priceStrategyFactory;
        this.showService = showService;

    }

    /**
     * The logic of selling one ticket, it uses price strategy
     * @param ticketEntity
     * @return a ticket with price, and specified performing hall
     * @throws IOException
     */
    public TicketEntity sellTicket(@Valid TicketEntity ticketEntity) throws IOException {
        PriceStrategy priceStrategy = priceStrategyFactory.getPriceStrategyInstance(ticketEntity.getPerformanceDate(),ticketEntity.getShowEntity());
        ticketEntity.setPrice(priceStrategy.calculatePrice(ticketEntity.getShowEntity()));
        ticketEntity.setHallEnum(findHall( ticketEntity));
        return ticketEntity;
     }

    /**
     * The logic of adding sold ticket each day
     * @param sellStatistic the statistic of sold ticket
     * @return added sold tickets statistic to the old statistic
     * @throws IOException
     */
    public SellStatistic addSoldTicketsEachDay(SellStatistic sellStatistic) throws IOException {
        sellTicket(sellStatistic.getTicketEntity());
        sellStatistic.setTotalSoldTicket(sellStatistic.getTicketEntity().getHallEnum().getSellPerDay()+sellStatistic.getTotalSoldTicket());
        return sellStatistic;
    }

    /**
     * The collector of all logic
     * @param filePath is the path of CSV file
     * @param queryDate is the date of query
     * @param showDate is the date of show
     * @return a list of sell statistics for all shows
     * @throws IOException
     */
    public List<SellStatistic> findStatusesFromCsv(String filePath, LocalDate queryDate, LocalDate showDate) throws IOException {
        /**
         * Reads the CSV file, and insert the date in our data inventory
         */
        showService.convertAllFromCsv(filePath);
        List<SellStatistic> sellStatisticList = new ArrayList<>();
        showService.findAllShows().forEach(showEntity ->
        {
            LocalDate startSellingDate = showDate.minusDays(howManyDaysBeforeSellingStarts);
            Long daysToShowDate = DAYS.between(queryDate,showDate);
            TicketEntity ticketEntity = new TicketEntity();
            ticketEntity.setShowEntity(showEntity);
            ticketEntity.setPerformanceDate(showDate);
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
            }


            sellStatisticList.add(sellStatistic);
        });
        return sellStatisticList;

    }

    /**
     * This method find a suitable hall for a ticket
     * @param ticketEntity
     * @return
     */
    protected HallEnum findHall(TicketEntity ticketEntity)
    {
        if(DAYS.between(ticketEntity.getShowEntity().getStartDate(),ticketEntity.getPerformanceDate())>bigHallToSmallHallCondition)
            return HallEnum.SMALL;
        return HallEnum.BIG;
    }

}
