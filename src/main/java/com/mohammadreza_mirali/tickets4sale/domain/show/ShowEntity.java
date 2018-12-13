package com.mohammadreza_mirali.tickets4sale.domain.show;



import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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

    public ShowEntity setGenre(GenreEnum genreEnum) {
        this.genreEnum = genreEnum;
        return this;
    }


    public LocalDate getEndDate(Short showValidPeriod)
    {
        return this.startDate.plusDays(showValidPeriod);
    }


}
