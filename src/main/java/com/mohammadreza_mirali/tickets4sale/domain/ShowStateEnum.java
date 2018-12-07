package com.mohammadreza_mirali.tickets4sale.domain;

public enum ShowStateEnum {
    SALE_NOT_STARTED {
        public String toString() {
            return "Sale not started";
        }
    },
    OPEN_FOR_SALE {
        public String toString() {
            return "Open for sale";
        }
    },
    SOLD_OUT {
        public String toString() {
            return "Sold out";
        }
    },
    IN_THE_PAST {
        public String toString() {
            return "In the past";
        }
    }

}
