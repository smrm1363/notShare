package com.mohammadreza_mirali.tickets4sale.domain.ticket;

import com.mohammadreza_mirali.tickets4sale.domain.ApplicationException;
import com.mohammadreza_mirali.tickets4sale.domain.HallEnum;
import com.mohammadreza_mirali.tickets4sale.domain.Inventory;
import com.mohammadreza_mirali.tickets4sale.domain.show.ShowEntity;
import com.mohammadreza_mirali.tickets4sale.domain.show.ShowService;
import com.mohammadreza_mirali.tickets4sale.domain.show.ShowStateEnum;
import com.mohammadreza_mirali.tickets4sale.domain.ticket.pricing.PriceStrategy;
import com.mohammadreza_mirali.tickets4sale.domain.ticket.pricing.PriceStrategyFactory;
import com.sun.xml.internal.bind.v2.TODO;
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


@Service
@Validated
public class TicketService {
//    private final TicketRepository ticketRepository;
    private final PriceStrategyFactory priceStrategyFactory;
    private final ShowService showService;


    @Value("${BIG_HALL_TO_SMALL_HALL_CONDITION}")
    private Short bigHallToSmallHallCondition;
    @Value("${HOW_MANY_DAYS_BEFORE_SELLING_STARTS}")
    private Short howManyDaysBeforeSellingStarts;

    @Autowired
    public TicketService(PriceStrategyFactory priceStrategyFactory, ShowService showService) {
//        this.ticketRepository = ticketRepository;
        this.priceStrategyFactory = priceStrategyFactory;
        this.showService = showService;

    }

    public TicketEntity sellTicket(@Valid TicketEntity ticketEntity) throws IOException, ApplicationException {
        if(ticketEntity.getSoldDate().isAfter(ticketEntity.getShowEntity().getEndDate()) || ticketEntity.getSoldDate().isAfter(ticketEntity.getTicketDate()))
            throw new ApplicationException("There is a problem in dates");
        PriceStrategy priceStrategy = priceStrategyFactory.getPriceStrategyInstance(ticketEntity.getTicketDate(),ticketEntity.getShowEntity());
        ticketEntity.setPrice(priceStrategy.calculatePrice(ticketEntity.getShowEntity()));
        ticketEntity.setHallEnum(findHall( ticketEntity));
        return ticketEntity;
     }

    public SellStatistic addSoldTicketsEachDay(SellStatistic sellStatistic) throws IOException, ApplicationException {
          sellTicket(sellStatistic.getTicketEntity());
//        List<TicketEntity> soldTicketList = new ArrayList<>();
//        SellStatistic sellStatistic = new SellStatistic();
//        sellStatistic.setTicketEntity(ticketEntity);
//        for (int x = 0;x<ticketEntity.getHallEnum().getSellPerDay();x++)
//        {
            sellStatistic.setTotalSoldTicket(sellStatistic.getTicketEntity().getHallEnum().getSellPerDay()+sellStatistic.getTotalSoldTicket());
//        }
        return sellStatistic;
    }

    public void findStatusesFromCsv(String filePath,LocalDate queryDate,LocalDate showDate) throws IOException {
        showService.saveAllFromCsv(filePath);
        showService.findAllShowes().forEach(showEntity ->
        {
            Long daysToShowDate = DAYS.between(showDate,queryDate);
            if(daysToShowDate<0)
            {
                showEntity.setShowStateEnum(ShowStateEnum.IN_THE_PAST);
            }
            else if(daysToShowDate<=howManyDaysBeforeSellingStarts)
            {
                TicketEntity ticketEntity = new TicketEntity();
                ticketEntity.setShowEntity(showEntity);
                ticketEntity.setTicketDate(showDate);
                SellStatistic sellStatistic = new SellStatistic();
                sellStatistic.setTicketEntity(ticketEntity);
                sellStatistic.setTotalSoldTicket(0);
                try {
                    while (sellStatistic.getTotalSoldTicket()<sellStatistic.getTotalAvailableTicket())
                    {
                        //TODO  break if queryDate is passing
                        addSoldTicketsEachDay(sellStatistic);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ApplicationException e) {
                    e.printStackTrace();
                }
                showEntity.setShowStateEnum(ShowStateEnum.OPEN_FOR_SALE);
            }
            else
            {
                showEntity.setShowStateEnum(ShowStateEnum.SALE_NOT_STARTED);
            }
        });
//        inventory.findShow()
//        TODO
//The main logic
    }

    private HallEnum findHall(TicketEntity ticketEntity)
    {
        if(DAYS.between(ticketEntity.getTicketDate(),ticketEntity.getShowEntity().getStartDate())>bigHallToSmallHallCondition)
            return HallEnum.SMALL;
        return HallEnum.BIG;
    }
}
