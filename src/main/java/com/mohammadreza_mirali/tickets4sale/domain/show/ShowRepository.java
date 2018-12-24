package com.mohammadreza_mirali.tickets4sale.domain.show;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by mmirali on 08/12/2018.
 */
@Component
public interface ShowRepository extends JpaRepository<ShowEntity,String> {
    ShowEntity findByTitleAndStartDateAndGenreEnum(String title, LocalDate startDate,GenreEnum genreEnum);


}
