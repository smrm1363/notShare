package com.mohammadreza_mirali.tickets4sale.domain.show.pricing;

import com.mohammadreza_mirali.tickets4sale.domain.show.GenreEnum;
import com.mohammadreza_mirali.tickets4sale.domain.show.ShowEntity;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class DiscountedPriceStrategyTest {
    @Test
    public void calculatePrice() throws Exception {
        DiscountedPriceStrategy discountedPriceStrategy = new DiscountedPriceStrategy();
        ShowEntity showEntity = new ShowEntity();
        showEntity.setTitle("Test show");
        showEntity.setGenre(GenreEnum.COMEDY);
        Integer result= discountedPriceStrategy.calculatePrice(showEntity);
        assertTrue(result==40);
    }

}