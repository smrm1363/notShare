package com.mohammadreza_mirali.tickets4sale.controller.dto;

import java.io.Serializable;

public class ShowOutPutDto extends ShowOutputDtoConsole implements Serializable {

    private Integer price;

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
