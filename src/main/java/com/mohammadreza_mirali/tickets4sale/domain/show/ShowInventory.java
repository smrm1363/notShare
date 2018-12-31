package com.mohammadreza_mirali.tickets4sale.domain.show;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a place for keeping show entities. Because we do not need database, I keep data in this structure
 */
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
