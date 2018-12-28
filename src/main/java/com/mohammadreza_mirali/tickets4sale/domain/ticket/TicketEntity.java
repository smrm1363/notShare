package com.mohammadreza_mirali.tickets4sale.domain.ticket;

import com.mohammadreza_mirali.tickets4sale.domain.show.ShowEntity;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class TicketEntity {

    @NotNull
    private LocalDate performancetDate;
    private Integer price;
    private HallEnum  hallEnum;
    @NotNull
    private ShowEntity showEntity;

    public LocalDate getPerformancetDate() {
        return performancetDate;
    }

    public void setPerformancetDate(LocalDate performancetDate) {
        this.performancetDate = performancetDate;
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

}
