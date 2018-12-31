package com.mohammadreza_mirali.tickets4sale.domain.ticket;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * This is a data structure for collecting statistic of sales
 */
public class SellStatistic implements Serializable{
    @NotNull
    private TicketEntity ticketEntity;
    private Integer totalSoldTicket = 0;
    private Integer totalAvailableTicket = 0;

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

    /**
     * It computes total available tickets
     * @return total available tickets
     */
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
