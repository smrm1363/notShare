package com.mohammadreza_mirali.tickets4sale.domain;



import java.time.LocalDate;


public class Show {
    private String title;
    private LocalDate startDate;
    private GenreEnum genreEnum;

    public Show(String title, LocalDate startDate, GenreEnum genreEnum) {
        this.title = title;
        this.startDate = startDate;
        this.genreEnum = genreEnum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public GenreEnum getGenreEnum() {
        return genreEnum;
    }

    public void setGenre(GenreEnum genreEnum) {
        this.genreEnum = genreEnum;
    }


    public LocalDate getEndDate(Short showValidPeriod)
    {
        return this.startDate.plusDays(showValidPeriod);
    }


}
