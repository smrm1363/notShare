package com.mohammadreza_mirali.tickets4sale.domain.ticket;

import com.mohammadreza_mirali.tickets4sale.domain.ApplicationException;
import com.mohammadreza_mirali.tickets4sale.domain.HallEnum;
import com.mohammadreza_mirali.tickets4sale.domain.Inventory;
import com.mohammadreza_mirali.tickets4sale.domain.show.ShowEntity;
import com.mohammadreza_mirali.tickets4sale.domain.show.ShowService;
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
    private final Inventory inventory;

    @Value("${BIG_HALL_TO_SMALL_HALL_CONDITION}")
    private Short bigHallToSmallHallCondition;

    @Autowired
    public TicketService(PriceStrategyFactory priceStrategyFactory, ShowService showService, Inventory inventory) {
//        this.ticketRepository = ticketRepository;
        this.priceStrategyFactory = priceStrategyFactory;
        this.showService = showService;
        this.inventory = inventory;
    }

    public TicketEntity sellTicket(@Valid TicketEntity ticketEntity) throws IOException, ApplicationException {
        if(ticketEntity.getSoldDate().isAfter(ticketEntity.getShowEntity().getEndDate()) || ticketEntity.getSoldDate().isAfter(ticketEntity.getTicketDate()))
            throw new ApplicationException("There is a problem in dates");
        PriceStrategy priceStrategy = priceStrategyFactory.getPriceStrategyInstance(ticketEntity.getTicketDate(),ticketEntity.getShowEntity());
        ticketEntity.setPrice(priceStrategy.calculatePrice(ticketEntity.getShowEntity()));
        ticketEntity.setHallEnum(findHall( ticketEntity));
        return ticketEntity;
     }

    public List<TicketEntity> sellTicketsEachDay(LocalDate performanceDate,@Valid ShowEntity showEntity) throws IOException, ApplicationException {
        TicketEntity ticketEntity = new TicketEntity();
        ticketEntity.setShowEntity(showEntity);
        ticketEntity.setTicketDate( performanceDate);
        ticketEntity = sellTicket(ticketEntity);
        List<TicketEntity> soldTicketList = new ArrayList<>();
        for (int x = 0;x<ticketEntity.getHallEnum().getSellPerDay();x++)
        {
            soldTicketList.add(ticketEntity.copy());
        }
        return soldTicketList;
    }

    public void findStatusesFromCsv(String filePath,LocalDate queryDate,LocalDate showDate) throws IOException {
        showService.saveAllFromCsv(filePath);
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
