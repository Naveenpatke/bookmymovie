package com.ayu.bookmymovie.Service;

import com.ayu.bookmymovie.DTO.CinemaDTO;
import com.ayu.bookmymovie.Model.Movie;
import com.ayu.bookmymovie.Model.Screen;
import com.ayu.bookmymovie.Repository.MovieRepository;
import com.ayu.bookmymovie.Repository.ScreenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.ayu.bookmymovie.Constants.BookMyMovieConstants.*;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    ScreenRepository screenRepository;

    public List<Movie> getMovieDetails(String movieName){
        return movieRepository.findByMovieName(movieName);
    }

    public List<CinemaDTO> getAllCinemaDetailsForGivenMovie(String movieName){
        List<Movie> movie = getMovieDetails(movieName);
        if (!movie.isEmpty()){
            return movieRepository.findCinemasByMovieID(movie.get(0).getMovieId());
        }
        return null;
    }

    public Movie addMovie(Movie movie){
        return movieRepository.save(movie);
    }

    public String addMovieToScreen(Long screenId, Long movieID){
        Optional<Screen> screen = screenRepository.findById(screenId);
        if (screen.isPresent()){
            Optional<Movie> movieFromDB = movieRepository.findById(movieID);
            if (movieFromDB.isPresent()){
                screen.get().setMovie(movieFromDB.get());
                screenRepository.save(screen.get());
                return MOVIE_ADDED_SUCCESSFULLY;
            }else {
                return NO_MOVIE_FOUND_FOR_GIVEN_ID;
            }
        }else {
            return NO_SCREEN_FOUND_FOR_GIVEN_ID;
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
        return MOVIE_DELETED_SUCCESSFULLY;
    }
}
