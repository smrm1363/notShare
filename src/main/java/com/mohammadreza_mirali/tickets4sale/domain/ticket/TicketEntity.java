package com.mohammadreza_mirali.tickets4sale.domain.ticket;

import com.mohammadreza_mirali.tickets4sale.domain.HallEnum;
import com.mohammadreza_mirali.tickets4sale.domain.show.ShowEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class TicketEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private String id;
    @NotNull
    private LocalDate ticketDate;
    @NotNull
    private LocalDate SoldDate;
    private Integer price;
    private HallEnum  hallEnum;
    @ManyToOne(fetch = FetchType.LAZY)
    private ShowEntity showEntity;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getTicketDate() {
        return ticketDate;
    }

    public void setTicketDate(LocalDate ticketDate) {
        this.ticketDate = ticketDate;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public HallEnum getHallEnum() {
        return hallEnum;
    }

    public void setHallEnum(HallEnum hallEnum) {
        this.hallEnum = hallEnum;
    }

    public ShowEntity getShowEntity() {
        return showEntity;
    }

    public void setShowEntity(ShowEntity showEntity) {
        this.showEntity = showEntity;
    }

    public LocalDate getSoldDate() {
        return SoldDate;
    }

    public void setSoldDate(LocalDate soldDate) {
        SoldDate = soldDate;
    }
}
