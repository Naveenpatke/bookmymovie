package com.ayu.bookmymovie.Service;

import com.ayu.bookmymovie.Model.Movie;
import com.ayu.bookmymovie.Model.Screen;
import com.ayu.bookmymovie.Repository.MovieRepository;
import com.ayu.bookmymovie.Repository.ScreenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    ScreenRepository screenRepository;

    public List<Movie> getMovieDetails(String movieName){
        return movieRepository.findByMovieName(movieName);
    }

    public Movie addMovie(Movie movie){
        return movieRepository.save(movie);
    }

    public String addMovieToScreen(Long screenId, Movie movie){
        Optional<Screen> screen = screenRepository.findById(screenId);
        if (screen.isPresent()){
            Movie movieFromDB = movieRepository.save(movie);
            screen.get().setMovie(movieFromDB);
            screenRepository.save(screen.get());
            return "Movie added successfully";
        }else {
            return "No Screen found for given ID";
        }
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
