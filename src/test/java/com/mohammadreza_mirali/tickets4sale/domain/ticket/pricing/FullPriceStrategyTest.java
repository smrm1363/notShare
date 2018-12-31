package com.mohammadreza_mirali.tickets4sale.domain.ticket.pricing;

import com.mohammadreza_mirali.tickets4sale.domain.show.GenreEnum;
import com.mohammadreza_mirali.tickets4sale.domain.show.ShowEntity;
import com.mohammadreza_mirali.tickets4sale.util.PropertiesLoader;
import org.junit.Test;

import java.util.Properties;

import static org.junit.Assert.*;

/**
 * Created by mmirali on 31/12/2018.
 */
public class FullPriceStrategyTest {
    @Test
    public void calculatePrice() throws Exception {
        FullPriceStrategy fullPriceStrategy = new FullPriceStrategy();
        ShowEntity showEntity = new ShowEntity().setGenreEnum(GenreEnum.COMEDY);
        Integer price = fullPriceStrategy.calculatePrice(showEntity);
        Properties properties = PropertiesLoader.loadProperties("application.properties");
        Integer priceOfGenre = Integer.valueOf(properties.getProperty(GenreEnum.COMEDY.name()));
        assertTrue(price.equals(priceOfGenre));
    }

}