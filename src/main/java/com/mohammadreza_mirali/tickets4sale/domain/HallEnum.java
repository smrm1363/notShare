package com.mohammadreza_mirali.tickets4sale.domain;

public enum  HallEnum {
    SMALL (5),
    BIG (10);


    private final int sellPerDay;

    private HallEnum(int c) {
        sellPerDay = c;
    }

    public int getSellPerDay()
    {
        return sellPerDay;
    }
    public boolean equalsName(int otherName) {

        return sellPerDay==otherName;
    }


}

