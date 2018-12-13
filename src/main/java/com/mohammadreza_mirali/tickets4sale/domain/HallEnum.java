package com.mohammadreza_mirali.tickets4sale.domain;

public enum  HallEnum {
    SMALL (100),
    BIG (200);


    private final int capacity;

    private HallEnum(int c) {
        capacity = c;
    }

    public boolean equalsName(int otherName) {

        return capacity==otherName;
    }


}

