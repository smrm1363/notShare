package com.mohammadreza_mirali.tickets4sale.domain;

public enum  GenreEnum {
    MUSICAL {
        public String toString() {
            return "Musical";
        }
    },
    COMEDY {
        public String toString() {
            return "Comedy";
        }
    },
    DRAMA {
        public String toString() {
            return "Drama";
        }
    }

}
