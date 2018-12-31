package com.mohammadreza_mirali.tickets4sale.domain.ticket.pricing;

import com.mohammadreza_mirali.tickets4sale.domain.show.ShowEntity;

import java.io.IOException;

/**
 * This is a Strategy pattern for finding the price of a show
 */
@FunctionalInterface
public interface PriceStrategy {
    /**
     * It calculates the price based on the suitable strategy
     * @param showEntity
     * @return the price
     * @throws IOException
     */
    Integer calculatePrice(ShowEntity showEntity) throws IOException;
}
