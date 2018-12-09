package com.mohammadreza_mirali.tickets4sale.domain;



import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;


public class Show {

    private String title;
    private LocalDate startDate;
    private GenreEnum genreEnum;


    public String getTitle() {
        return title;
    }


    public Show setTitle(@NotNull @Size(min = 1) String title) {
        this.title = title;
        return this;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public Show setStartDate(@Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$") LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public GenreEnum getGenreEnum() {
        return genreEnum;
    }

    public Show setGenre(GenreEnum genreEnum) {
        this.genreEnum = genreEnum;
        return this;
    }


    public LocalDate getEndDate(Short showValidPeriod)
    {
        return this.startDate.plusDays(showValidPeriod);
    }


}
