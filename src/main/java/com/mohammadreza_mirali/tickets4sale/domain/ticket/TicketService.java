package com.mohammadreza_mirali.tickets4sale.domain.ticket;

import com.mohammadreza_mirali.tickets4sale.domain.ticket.pricing.PriceStrategy;
import com.mohammadreza_mirali.tickets4sale.domain.ticket.pricing.PriceStrategyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.io.IOException;


@Service
@Validated
public class TicketService {
    private final TicketRepository ticketRepository;
    private final PriceStrategyFactory priceStrategyFactory;

    @Autowired
    public TicketService(TicketRepository ticketRepository, PriceStrategyFactory priceStrategyFactory) {
        this.ticketRepository = ticketRepository;
        this.priceStrategyFactory = priceStrategyFactory;
    }

    public void sellTicket(@Valid TicketEntity ticketEntity) throws IOException {
        if(!ticketEntity.getSoldDate().isAfter(ticketEntity.getShowEntity().getEndDate()))
        {
            if(!ticketEntity.getSoldDate().isAfter(ticketEntity.getTicketDate()))
            {
                PriceStrategy priceStrategy = priceStrategyFactory.getPriceStrategyInstance(ticketEntity.getTicketDate(),ticketEntity.getShowEntity());
                ticketEntity.setPrice(priceStrategy.calculatePrice(ticketEntity.getShowEntity()));
            }
        }

        List

    }
}
