package com.ayu.bookmymovie.Repository;

import com.ayu.bookmymovie.DTO.CinemaDTO;
import com.ayu.bookmymovie.Model.Cinema;
import com.ayu.bookmymovie.Model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query(value = "SELECT * FROM movie m WHERE lower(m.movie_name) LIKE %?1%",
            nativeQuery = true)
    List<Movie> findByMovieName(String movieName);

    @Query(value = "SELECT c.cinema_id as id, c.address as address, c.cinema_name as cinemaName, c.rating, s.id as screenID, s.timings FROM cinema c " +
            "INNER JOIN screen s ON c.cinema_id = s.cinema_cinema_id INNER JOIN movie m ON s.movie_movie_id = m.movie_id  " +
            "WHERE m.movie_id=?1",
            nativeQuery = true)
    List<CinemaDTO> findCinemasByMovieID(Long movieName);
}