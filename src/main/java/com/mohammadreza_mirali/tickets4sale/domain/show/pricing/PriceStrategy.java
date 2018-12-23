package com.mohammadreza_mirali.tickets4sale.domain.show.pricing;

import com.mohammadreza_mirali.tickets4sale.domain.show.GenreEnum;
import com.mohammadreza_mirali.tickets4sale.domain.show.ShowEntity;

import java.io.IOException;
import java.time.LocalDate;

@FunctionalInterface
public interface PriceStrategy {
    Integer calculatePrice(ShowEntity showEntity) throws IOException;
}
