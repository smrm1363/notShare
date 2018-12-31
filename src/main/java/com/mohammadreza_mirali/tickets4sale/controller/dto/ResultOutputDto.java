package com.mohammadreza_mirali.tickets4sale.controller.dto;

import java.io.Serializable;
import java.util.List;

/**
 * This is a DTO for our result which should be in our JSON result.
 */
public class ResultOutputDto implements Serializable{
    private String genre;
    private List<ShowOutputDtoConsole> shows;

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<ShowOutputDtoConsole> getShows() {
        return shows;
    }

    public void setShows(List<ShowOutputDtoConsole> shows) {
        this.shows = shows;
    }
}
