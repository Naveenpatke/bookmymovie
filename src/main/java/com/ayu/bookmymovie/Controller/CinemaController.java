package com.ayu.bookmymovie.Controller;

import com.ayu.bookmymovie.Model.Cinema;
import com.ayu.bookmymovie.Service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/cinema")
public class CinemaController {

    @Autowired
    CinemaService cinemaService;

    @GetMapping("/get-cinema-details")
    public Optional<Cinema> getCategoryDetails(@RequestParam Long cinemaId){
        return cinemaService.getCinemaDetails(cinemaId);
    }

    @PostMapping("/add-cinema")
    public Cinema addScreen(@RequestBody Cinema cinema){
        return cinemaService.addCinema(cinema);
    }

    @PutMapping("/update-cinema-details")
    public Cinema updateCinemaDetails(@RequestBody Cinema cinema){
        return cinemaService.updateCinemaDetails(cinema);
    }

    @DeleteMapping("/delete-cinema")
    public String deleteCinema(@RequestParam Long cinemaId){
        return cinemaService.deleteCinema(cinemaId);
    }
}
