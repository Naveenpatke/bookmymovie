package com.ayu.bookmymovie.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

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

    private Integer totalSeatsBooked;

    @ManyToOne
    @JoinColumn(name = "screen_id")
    @JsonIgnore
    private Screen screen;

    @OneToMany(mappedBy = "layoutCategory")
    private List<ReservedSeats> reservedSeats;
}