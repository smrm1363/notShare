package com.mohammadreza_mirali.tickets4sale.domain.show.pricing;

import com.mohammadreza_mirali.tickets4sale.domain.show.ShowEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;


@Component
public class PriceStrategyFactory {
    private final short discountCriterionDays = 80;
    public PriceStrategy getInstance(LocalDate performanceDate, ShowEntity showEntity)
    {
        PriceStrategy priceStrategy;
        if(DAYS.between(showEntity.getStartDate(),performanceDate)>discountCriterionDays)
            priceStrategy = new DiscountedPriceStrategy();
        else
            priceStrategy = new FullPriceStrategy();
        return priceStrategy;
    }
}
