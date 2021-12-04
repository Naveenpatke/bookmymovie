package com.ayu.bookmymovie.Service;

import com.ayu.bookmymovie.Model.Movie;
import com.ayu.bookmymovie.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public Movie getMovieDetails(String movieName){
        return movieRepository.findByMovieName(movieName);
    }

    public Movie addMovie(Movie movie){
        return movieRepository.save(movie);
    }

    public Movie updateMovieDetails(Movie movie){
        return movieRepository.save(movie);
    }

    public String deleteMovie(@RequestParam Long movieId){
        movieRepository.deleteById(movieId);
        return "Deleted Successfully";
    }
}
