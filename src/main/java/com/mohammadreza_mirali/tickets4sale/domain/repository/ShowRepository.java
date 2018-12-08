package com.mohammadreza_mirali.tickets4sale.domain.repository;

import com.mohammadreza_mirali.tickets4sale.domain.Inventory;
import com.mohammadreza_mirali.tickets4sale.domain.Show;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mmirali on 08/12/2018.
 */
@Component
public class ShowRepository {
    @Autowired
    Inventory inventory;

    public void create(Show show)
    {
        inventory.getShowList().add(show);
    }
}
