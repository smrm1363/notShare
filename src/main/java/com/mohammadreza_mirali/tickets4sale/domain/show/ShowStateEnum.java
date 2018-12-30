package com.mohammadreza_mirali.tickets4sale.domain.show;

public enum ShowStateEnum {
    SALE_NOT_STARTED ("Sale not started"),
    OPEN_FOR_SALE ("Open for sale"),
    SOLD_OUT ("Sold out"),
    IN_THE_PAST("In the past")    ;

    private final String name;

    ShowStateEnum(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        return name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }

}
