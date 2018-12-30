package com.mohammadreza_mirali.tickets4sale.domain.show;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ShowInventory {
    private List<ShowEntity> showEntityList = new ArrayList<>();

    public List<ShowEntity> getShowEntityList() {
        return showEntityList;
    }

    public void setShowEntityList(List<ShowEntity> showEntityList) {
        this.showEntityList = showEntityList;
    }


}
