package com.mohammadreza_mirali.tickets4sale.domain;

import com.mohammadreza_mirali.tickets4sale.domain.show.ShowEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Inventory {
    private List<ShowEntity> showEntityList = new ArrayList<>();

    public List<ShowEntity> getShowEntityList() {
        return showEntityList;
    }

    public void setShowEntityList(List<ShowEntity> showEntityList) {
        this.showEntityList = showEntityList;
    }

}
