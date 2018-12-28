package com.mohammadreza_mirali.tickets4sale.domain.ticket;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class SellStatistic implements Serializable{
    @NotNull
    private TicketEntity ticketEntity;
    private Integer totalSoldTicket;
    private Integer totalAvailableTicket;

    public TicketEntity getTicketEntity() {
        return ticketEntity;
    }

    public void setTicketEntity(TicketEntity ticketEntity) {
        this.ticketEntity = ticketEntity;
    }

    public Integer getTotalSoldTicket() {
        return totalSoldTicket;
    }

    public void setTotalSoldTicket(Integer totalSoldTicket) {
        this.totalSoldTicket = totalSoldTicket;
    }

    public Integer getTotalAvailableTicket()
    {
        if(ticketEntity.getHallEnum() != null)
            return ticketEntity.getHallEnum().getCapacity()-totalSoldTicket;
        else
            return 0;
    }
    public void setTotalAvailableTicket(Integer totalAvailableTicket)
    {
        this.totalAvailableTicket = totalAvailableTicket;
    }
}
