package com.mohammadreza_mirali.tickets4sale.domain.ticket.pricing;

import com.mohammadreza_mirali.tickets4sale.domain.show.ShowEntity;
import com.mohammadreza_mirali.tickets4sale.util.PropertiesLoader;

import java.io.IOException;
import java.util.Properties;

public class DiscountedPriceStrategy implements PriceStrategy {
    private final String discountPropertyKey = "80PERCENT";
    @Override
    public Integer calculatePrice(ShowEntity showEntity) throws IOException {
        Properties properties = PropertiesLoader.loadProperties("application.properties");

        Integer priceOfGenre = Integer.valueOf(properties.getProperty(showEntity.getGenreEnum().name()));
        Double discountPercent = Double.valueOf(properties.getProperty(discountPropertyKey));

        return Math.toIntExact(Math.round(discountPercent * .01 * priceOfGenre));
    }
}
