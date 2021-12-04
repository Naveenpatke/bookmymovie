package com.ayu.bookmymovie.Controller;

import com.ayu.bookmymovie.Model.Screen;
import com.ayu.bookmymovie.Service.CinemaService;
import com.ayu.bookmymovie.Service.ScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/screen")
public class ScreenController {

    @Autowired
    ScreenService screenService;

    @Autowired
    CinemaService cinemaService;

    @GetMapping("/get-screen-details")
    public Optional<Screen> getScreenDetails(@RequestParam Long screenId){
        return screenService.getScreenDetails(screenId);
    }

    @PostMapping("/add-screen")
    public Screen addScreen(@RequestBody Screen screen){
        return screenService.addScreen(screen);
    }

    @PostMapping("/add-screen-to-cinema")
    public String addLayoutCategoryToScreen(@RequestParam Long cinemaId, @RequestBody Screen screen){
        return screenService.addScreenToCinema(cinemaId, screen);
    }

    @PutMapping("/update-screen-details")
    public Screen updateScreenDetails(@RequestBody Screen screen){
        return screenService.updateScreenDetails(screen);
    }

    @DeleteMapping("/delete-screen")
    public String deleteScreen(@RequestParam Long screenId){
        return screenService.deleteScreen(screenId);
    }
}
