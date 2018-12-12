package com.mohammadreza_mirali.tickets4sale.domain.show;

public enum  GenreEnum {
    MUSICAL ("Musical"),
    COMEDY ("Comedy"),
    DRAMA ("Drama");

    private final String name;

    private GenreEnum(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        // (otherName == null) check is not needed because name.equals(null) returns false
        return name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }
}
