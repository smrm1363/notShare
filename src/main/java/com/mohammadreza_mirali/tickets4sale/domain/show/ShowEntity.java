package com.mohammadreza_mirali.tickets4sale.domain.show;



import com.mohammadreza_mirali.tickets4sale.domain.ticket.TicketEntity;
import com.mohammadreza_mirali.tickets4sale.util.PropertiesLoader;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Table(
        uniqueConstraints=
        @UniqueConstraint(columnNames={"title", "startDate","genreEnum"})
)
@Entity
public class ShowEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private String id;

    @NotNull
    private String title;
    @NotNull
    private LocalDate startDate;
    @NotNull
    private GenreEnum genreEnum;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "showEntity")
    private List<TicketEntity> ticketEntityList;
    private ShowStateEnum showStateEnum;


    public String getTitle() {
        return title;
    }


    public ShowEntity setTitle(@Size(min = 1) String title) {
        this.title = title;
        return this;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public ShowEntity setStartDate(@Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$") LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public GenreEnum getGenreEnum() {
        return genreEnum;
    }

    public ShowEntity setGenreEnum(GenreEnum genreEnum) {
        this.genreEnum = genreEnum;
        return this;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<TicketEntity> getTicketEntityList() {
        return ticketEntityList;
    }

    public void setTicketEntityList(List<TicketEntity> ticketEntityList) {
        this.ticketEntityList = ticketEntityList;
    }

    public ShowStateEnum getShowStateEnum() {
        return showStateEnum;
    }

    public void setShowStateEnum(ShowStateEnum showStateEnum) {
        this.showStateEnum = showStateEnum;
    }

    public short getValidPeriod() throws IOException {
       return Short.parseShort(PropertiesLoader.loadProperties("application.properties").getProperty("show_valid_period"));

    }
    public LocalDate getEndDate() throws IOException {
        return this.startDate.plusDays(getValidPeriod());
    }


}
