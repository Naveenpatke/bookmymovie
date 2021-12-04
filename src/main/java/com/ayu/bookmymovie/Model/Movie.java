package com.ayu.bookmymovie.Model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "movie_id", nullable = false)
    private Long movieId;

    private String movieName;

    private String movieDuration;

    private String movieDetails;

    @OneToMany(mappedBy = "movie")
    private List<Screen> screen;
}