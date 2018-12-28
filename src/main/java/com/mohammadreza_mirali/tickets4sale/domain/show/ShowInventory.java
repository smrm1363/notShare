package com.mohammadreza_mirali.tickets4sale.domain.show;

import com.mohammadreza_mirali.tickets4sale.domain.show.ShowEntity;
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

    public ShowEntity findShow(ShowEntity showEntity)
    {
        Optional<ShowEntity> showEntityOptional = showEntityList.stream().filter(showEntity1 ->
        {
            if(showEntity1.getTitle().equals(showEntity.getTitle()) && showEntity1.getStartDate().equals(showEntity.getStartDate()))
                return true;
            return false;
        }).findAny();
        return showEntityOptional.get();
    }

}
