package com.mohammadreza_mirali.tickets4sale.domain.show;

import com.mohammadreza_mirali.tickets4sale.util.PropertiesLoader;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.IOException;
import java.time.LocalDate;

public class ShowEntity {

    @NotNull
    private String title;
    @NotNull
    private LocalDate startDate;
    @NotNull
    private GenreEnum genreEnum;
    private ShowStateEnum showStateEnum;


    public String getTitle() {
        return title;
    }


    public ShowEntity setTitle(@Size(min = 1) String title) {
        this.title = title;
        return this;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public ShowEntity setStartDate(@Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$") LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public GenreEnum getGenreEnum() {
        return genreEnum;
    }

    public ShowEntity setGenreEnum(GenreEnum genreEnum) {
        this.genreEnum = genreEnum;
        return this;
    }

    public ShowStateEnum getShowStateEnum() {
        return showStateEnum;
    }

    public void setShowStateEnum(ShowStateEnum showStateEnum) {
        this.showStateEnum = showStateEnum;
    }

    public short getValidPeriod() throws IOException {
       return Short.parseShort(PropertiesLoader.loadProperties("application.properties").getProperty("show_valid_period"));

    }
    public LocalDate getEndDate() throws IOException {
        return this.startDate.plusDays(getValidPeriod());
    }


}
