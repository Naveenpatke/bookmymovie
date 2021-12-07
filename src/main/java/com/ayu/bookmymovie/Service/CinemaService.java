package com.ayu.bookmymovie.Service;

import com.ayu.bookmymovie.Model.Cinema;
import com.ayu.bookmymovie.Repository.CinemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.ayu.bookmymovie.Constants.BookMyMovieConstants.CINEMA_DELETED_SUCCESSFULLY;

@Service
public class CinemaService {

    @Autowired
    CinemaRepository cinemaRepository;

    public Optional<Cinema> getCinemaDetails(Long cinemaId){
        return cinemaRepository.findById(cinemaId);
    }

    public Cinema addCinema(Cinema cinema){
        return cinemaRepository.save(cinema);
    }

    public Cinema updateCinemaDetails(Cinema cinema){
        Optional<Cinema> cinemaDetailsFromDB = cinemaRepository.findById(cinema.getCinema_id());
        if(cinemaDetailsFromDB.isPresent()){
            cinemaDetailsFromDB.get().setCinemaName(cinema.getCinemaName());
            cinemaDetailsFromDB.get().setAddress(cinema.getAddress());
            cinemaDetailsFromDB.get().setRating(cinema.getRating());
            return cinemaRepository.save(cinemaDetailsFromDB.get());
        } else {
            return new Cinema();
        }
    }

    public String deleteCinema(Long cinemaId){
        cinemaRepository.deleteById(cinemaId);
        return CINEMA_DELETED_SUCCESSFULLY;
    }
}
