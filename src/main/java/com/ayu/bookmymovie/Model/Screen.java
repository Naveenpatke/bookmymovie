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
@Table(name = "screen")
public class Screen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String screenName;

    private String timings;

    @ManyToOne
    @JoinColumn(name = "cinema_cinema_id")
    @JsonIgnore
    private Cinema cinema;

    @OneToOne
    @JoinColumn(name = "movie_movie_id")
    private Movie movie;

    @OneToMany(mappedBy = "screen")
    private List<LayoutCategory> layoutCategories;

    @OneToMany(mappedBy = "screen")
    @JsonIgnore
    private List<ReservedSeats> reservedSeats;
}