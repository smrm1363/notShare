package com.mohammadreza_mirali.tickets4sale.domain.ticket;

public enum  HallEnum {
    SMALL (5, 100),
    BIG (10, 200);


    private final int sellPerDay;
    private final int capacity;

    private HallEnum(int sellPerDay, int capacity) {
        this.sellPerDay = sellPerDay;
        this.capacity = capacity;
    }

    public int getSellPerDay()
    {
        return sellPerDay;
    }

    public int getCapacity() {
        return capacity;
    }

}

