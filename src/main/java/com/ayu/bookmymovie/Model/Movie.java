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
@Table(name = "movie")

public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id", nullable = false)
    private Long movieId;

    private String movieName;

    private String movieDuration;

    private String movieDetails;

    @OneToMany(mappedBy = "movie")
    @JsonIgnore
    private List<Screen> screen;
}