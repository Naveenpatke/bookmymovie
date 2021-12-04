package com.ayu.bookmymovie.Service;

import com.ayu.bookmymovie.Model.Movie;
import com.ayu.bookmymovie.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public List<Movie> getMovieDetails(String movieName){
        return movieRepository.findByMovieName(movieName);
    }

    public Movie addMovie(Movie movie){
        return movieRepository.save(movie);
    }

    public Movie updateMovieDetails(Movie movie){
        Optional<Movie> movieDetailsFromDB = movieRepository.findById(movie.getMovieId());
        if(movieDetailsFromDB.isPresent()){
            return movieRepository.save(movie);
        } else {
           return new Movie();
        }
    }

    public String deleteMovie(Long movieId){
        movieRepository.deleteById(movieId);
        return "Deleted Successfully";
    }
}
