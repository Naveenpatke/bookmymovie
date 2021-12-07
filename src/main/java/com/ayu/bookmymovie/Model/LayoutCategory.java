package com.ayu.bookmymovie.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.ayu.bookmymovie.Constants.BookMyMovieConstants.CANCELLED;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "layoutCategory")
public class LayoutCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String categoryName;

    private Double price;

    private Integer maxSeats;

    @ManyToOne
    @JoinColumn(name = "screen_id")
    @JsonIgnore
    private Screen screen;

    @OneToMany(mappedBy = "layoutCategory")
    private List<ReservedSeats> reservedSeats;

    public List<ReservedSeats> getReservedSeats() {
        return reservedSeats.stream()
                .filter(reservedSeat -> !Objects.equals(reservedSeat.getBookingStatus(), CANCELLED))
                .collect(Collectors.toList());
    }
}