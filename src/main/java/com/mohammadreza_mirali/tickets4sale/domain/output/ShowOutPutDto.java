package com.mohammadreza_mirali.tickets4sale.domain.output;

import com.mohammadreza_mirali.tickets4sale.domain.show.GenreEnum;
import com.mohammadreza_mirali.tickets4sale.domain.show.ShowStateEnum;
import com.mohammadreza_mirali.tickets4sale.domain.ticket.SellStatistic;

import java.io.Serializable;
import java.util.List;

public class ShowOutPutDto implements Serializable {
    private String title;
    private Integer ticketsLeft;
    private Integer ticketsAvailable;
    private ShowStateEnum status;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getTicketsLeft() {
        return ticketsLeft;
    }

    public void setTicketsLeft(Integer ticketsLeft) {
        this.ticketsLeft = ticketsLeft;
    }

    public Integer getTicketsAvailable() {
        return ticketsAvailable;
    }

    public void setTicketsAvailable(Integer ticketsAvailable) {
        this.ticketsAvailable = ticketsAvailable;
    }

    public ShowStateEnum getStatus() {
        return status;
    }

    public void setStatus(ShowStateEnum status) {
        this.status = status;
    }
}
