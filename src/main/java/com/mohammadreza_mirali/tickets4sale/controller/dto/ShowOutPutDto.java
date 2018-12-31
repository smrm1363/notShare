package com.mohammadreza_mirali.tickets4sale.controller.dto;

import java.io.Serializable;

/**
 * This is a DTO for our result which should be in our JSON result, It contains Price for web application
 */
public class ShowOutPutDto extends ShowOutputDtoConsole implements Serializable {

    private Integer price;

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
