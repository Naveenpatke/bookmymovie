package com.ayu.bookmymovie.Controller;

import com.ayu.bookmymovie.DTO.CinemaDTO;
import com.ayu.bookmymovie.Model.Cinema;
import com.ayu.bookmymovie.Model.Movie;
import com.ayu.bookmymovie.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    MovieService movieService;

    @GetMapping("/get-movie-details")
    public List<Movie> getMovieDetails(@RequestParam String movieName){
        return movieService.getMovieDetails(movieName);
    }

    @GetMapping("/get-all-cinema-details-by-movie")
    public List<CinemaDTO> getAllCinemaDetailsByMovie(@RequestParam String movieName){
        return movieService.getAllCinemaDetailsForGivenMovie(movieName);
    }

    @PostMapping("/add-movie")
    public Movie addMovie(@RequestBody Movie movie){
        return movieService.addMovie(movie);
    }


    @PostMapping("/add-movie-to-screen")
    public String addLayoutCategoryToScreen(@RequestParam Long screenId, @RequestParam Long movieID){
        return movieService.addMovieToScreen(screenId, movieID);
    }

    @PutMapping("/update-movie-details")
    public Movie updateMovieDetails(@RequestBody Movie movie){
        return movieService.updateMovieDetails(movie);
    }

    @DeleteMapping("/delete-movie")
    public String deleteMovie(@RequestParam Long movieId){
        return movieService.deleteMovie(movieId);
    }
}
