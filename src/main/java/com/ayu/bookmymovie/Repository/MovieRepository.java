package com.ayu.bookmymovie.Repository;

import com.ayu.bookmymovie.Model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query(value = "SELECT * FROM movie m WHERE lower(m.movie_name) LIKE %?1%",
            nativeQuery = true)
    List<Movie> findByMovieName(String movieName);

}