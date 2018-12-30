package com.mohammadreza_mirali.tickets4sale.domain.ticket.pricing;

import com.mohammadreza_mirali.tickets4sale.domain.show.ShowEntity;

import java.io.IOException;

@FunctionalInterface
public interface PriceStrategy {
    Integer calculatePrice(ShowEntity showEntity) throws IOException;
}
