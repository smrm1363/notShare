package com.mohammadreza_mirali.tickets4sale.domain;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Inventory {
    private List<Show> showList = new ArrayList<>();

    public List<Show> getShowList() {
        return showList;
    }

    public void setShowList(List<Show> showList) {
        this.showList = showList;
    }

}
