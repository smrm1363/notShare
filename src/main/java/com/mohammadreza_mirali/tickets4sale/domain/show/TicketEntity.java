package com.mohammadreza_mirali.tickets4sale.domain.show;

import com.mohammadreza_mirali.tickets4sale.domain.HallEnum;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class TicketEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private String id;
    @NotNull
    private LocalDate ticketDate;
    private Integer price;
    private HallEnum  hallEnum;
    @ManyToOne(fetch = FetchType.LAZY)
    private ShowEntity showEntity;

}
