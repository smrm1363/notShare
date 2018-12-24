package com.mohammadreza_mirali.tickets4sale.domain.ticket.pricing;

import com.mohammadreza_mirali.tickets4sale.domain.show.ShowEntity;
import com.mohammadreza_mirali.tickets4sale.util.PropertiesLoader;

import java.io.IOException;
import java.util.Properties;

public class FullPriceStrategy implements PriceStrategy {
    @Override
    public Integer calculatePrice(ShowEntity showEntity) throws IOException {
        Properties properties = PropertiesLoader.loadProperties("application.properties");

        Integer priceOfGenre = Integer.valueOf(properties.getProperty(showEntity.getGenreEnum().toString()));

        return priceOfGenre;
    }
}
