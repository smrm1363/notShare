package com.mohammadreza_mirali.tickets4sale.domain.output;

import java.io.Serializable;
import java.util.List;

public class ResultOutputDto implements Serializable{
    private String genre;
    private List<ShowOutPutDto> shows;

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<ShowOutPutDto> getShows() {
        return shows;
    }

    public void setShows(List<ShowOutPutDto> shows) {
        this.shows = shows;
    }
}
