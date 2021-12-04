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

@Service
public class ScreenService {

    @Autowired
    ScreenRepository screenRepository;

    @Autowired
    CinemaRepository cinemaRepository;

    public Optional<Screen> getScreenDetails(Long screenId){
        return screenRepository.findById(screenId);
    }

    public Screen addScreen(Screen screen){
        return screenRepository.save(screen);
    }

    public String addScreenToCinema(Long cinemaId, Screen screen){
        Optional<Cinema> cinema = cinemaRepository.findById(cinemaId);
        if (cinema.isPresent()){
            List<Screen> screens = cinema.get().getScreens();
            screen.setCinema(cinema.get());
            Screen screenDetailsFromDB = screenRepository.save(screen);
            screens.add(screenDetailsFromDB);
            cinema.get().setScreens(screens);
            cinemaRepository.save(cinema.get());
            return "Screen added to cinema successfully";
        }else {
            return "No Cinema found for given ID";
        }
    }

    public Screen updateScreenDetails(Screen screen){
        Optional<Screen> screenDetailsFromDB = screenRepository.findById(screen.getId());
        if(screenDetailsFromDB.isPresent()){
            return screenRepository.save(screen);
        } else {
            return new Screen();
        }
    }

    public String deleteScreen(Long screenId){
        screenRepository.deleteById(screenId);
        return "Deleted Successfully";
    }
}
