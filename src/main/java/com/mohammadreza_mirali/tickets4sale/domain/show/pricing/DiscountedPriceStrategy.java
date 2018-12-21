package com.mohammadreza_mirali.tickets4sale.domain.show.pricing;

import com.mohammadreza_mirali.tickets4sale.domain.show.ShowEntity;
import com.mohammadreza_mirali.tickets4sale.util.PropertiesLoader;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Properties;

public class DiscountedPriceStrategy implements PriceStrategy {
    private final String discountPropertuKey = "80PERCENT";
    @Override
    public Integer calculatePrice(LocalDate PerformanceDate, ShowEntity showEntity) throws IOException {
        Properties properties = PropertiesLoader.loadProperties("application.properties");

        Integer priceOfGenre = Integer.valueOf(properties.getProperty(showEntity.getGenreEnum().toString()));
        Double discountPercent = Double.valueOf(properties.getProperty(discountPropertuKey));

        return Math.toIntExact(Math.round(discountPercent * .01) * priceOfGenre);
    }
}
