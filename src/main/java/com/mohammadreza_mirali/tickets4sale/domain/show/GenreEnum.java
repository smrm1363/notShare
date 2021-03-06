package com.mohammadreza_mirali.tickets4sale.domain.show;

public enum  GenreEnum {
    MUSICAL ("Musical"),
    COMEDY ("Comedy"),
    DRAMA ("Drama");

    private final String name;

    GenreEnum(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        return name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }
}
