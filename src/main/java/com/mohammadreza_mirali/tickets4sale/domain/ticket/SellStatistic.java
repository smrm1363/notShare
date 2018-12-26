package com.mohammadreza_mirali.tickets4sale.domain.ticket;

public class SellStatistic {
    private TicketEntity ticketEntity;
    private Integer totalSoldTicket;

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
        return ticketEntity.getHallEnum().getCapacity()-totalSoldTicket;
    }
}
