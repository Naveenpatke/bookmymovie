package com.ayu.bookmymovie.Service;

import com.ayu.bookmymovie.Model.Cinema;
import com.ayu.bookmymovie.Model.LayoutCategory;
import com.ayu.bookmymovie.Model.Screen;
import com.ayu.bookmymovie.Repository.CinemaRepository;
import com.ayu.bookmymovie.Repository.LayoutCategoryRepository;
import com.ayu.bookmymovie.Repository.ScreenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.ayu.bookmymovie.Constants.BookMyMovieConstants.CINEMA_DELETED_SUCCESSFULLY;

@Service
public class CinemaService {

    @Autowired
    CinemaRepository cinemaRepository;

    @Autowired
    ScreenRepository screenRepository;

    public Optional<Cinema> getCinemaDetails(Long cinemaId){
        return cinemaRepository.findById(cinemaId);
    }

    public Cinema addCinema(Cinema cinema){
        return cinemaRepository.save(cinema);
    }

    public Cinema updateCinemaDetails(Cinema cinema){
        Optional<Cinema> cinemaDetailsFromDB = cinemaRepository.findById(cinema.getCinema_id());
        if(cinemaDetailsFromDB.isPresent()){
            return cinemaRepository.save(cinema);
        } else {
            return new Cinema();
        }
    }

    public String deleteCinema(Long cinemaId){
        cinemaRepository.deleteById(cinemaId);
        return CINEMA_DELETED_SUCCESSFULLY;
    }
}
