package com.mohammadreza_mirali.tickets4sale.domain.ticket.pricing;

import com.mohammadreza_mirali.tickets4sale.domain.show.ShowEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * This is the factory class for making a suitable strategy for finding price
 */
@Component
public class PriceStrategyFactory {
    private final short discountCriterionDays = 80;

    /**
     * This contains the conditions for determining the true strategy
     * @param performanceDate
     * @param showEntity
     * @return
     */
    public PriceStrategy getPriceStrategyInstance(LocalDate performanceDate, ShowEntity showEntity)
    {
        PriceStrategy priceStrategy;
        if(DAYS.between(showEntity.getStartDate(),performanceDate)>discountCriterionDays)
            priceStrategy = new DiscountedPriceStrategy();
        else
            priceStrategy = new FullPriceStrategy();
        return priceStrategy;
    }
}
