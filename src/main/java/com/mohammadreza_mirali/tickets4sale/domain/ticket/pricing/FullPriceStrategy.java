package com.mohammadreza_mirali.tickets4sale.domain.ticket.pricing;

import com.mohammadreza_mirali.tickets4sale.domain.show.ShowEntity;
import com.mohammadreza_mirali.tickets4sale.util.PropertiesLoader;

import java.io.IOException;
import java.util.Properties;

/**
 * This is the strategy for full price shows
 */
public class FullPriceStrategy implements PriceStrategy {
    @Override
    public Integer calculatePrice(ShowEntity showEntity) throws IOException {
        /**
         * The price of each genre is a configurable variable in the application.properties
         */
        Properties properties = PropertiesLoader.loadProperties("application.properties");

        return Integer.valueOf(properties.getProperty(showEntity.getGenreEnum().name()));
    }
}
