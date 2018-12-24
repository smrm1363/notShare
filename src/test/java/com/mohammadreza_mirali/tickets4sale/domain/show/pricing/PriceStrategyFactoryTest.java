package com.mohammadreza_mirali.tickets4sale.domain.show.pricing;

import com.mohammadreza_mirali.tickets4sale.domain.show.GenreEnum;
import com.mohammadreza_mirali.tickets4sale.domain.show.ShowEntity;
import com.mohammadreza_mirali.tickets4sale.domain.ticket.pricing.DiscountedPriceStrategy;
import com.mohammadreza_mirali.tickets4sale.domain.ticket.pricing.FullPriceStrategy;
import com.mohammadreza_mirali.tickets4sale.domain.ticket.pricing.PriceStrategy;
import com.mohammadreza_mirali.tickets4sale.domain.ticket.pricing.PriceStrategyFactory;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class PriceStrategyFactoryTest {
    @Test
    public void calculatePrice() throws Exception {
        PriceStrategyFactory priceStrategyFactory = new PriceStrategyFactory();
        ShowEntity showEntity = new ShowEntity();
        showEntity.setTitle("Test show");
        showEntity.setGenreEnum(GenreEnum.COMEDY);
        showEntity.setStartDate(LocalDate.of(2018,9,10));
        PriceStrategy priceStrategy= priceStrategyFactory.getPriceStrategyInstance(LocalDate.of(2018,12,1),showEntity);
        assertTrue(priceStrategy instanceof DiscountedPriceStrategy);
        showEntity.setStartDate(LocalDate.of(2018,11,10));
        PriceStrategy priceStrategy2= priceStrategyFactory.getPriceStrategyInstance(LocalDate.of(2018,12,1),showEntity);
        assertTrue(priceStrategy2 instanceof FullPriceStrategy);

    }


}