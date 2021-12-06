package com.ayu.bookmymovie.Controller;

import com.ayu.bookmymovie.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    BookingService bookingService;

    @PostMapping("/reserve-ticket")
    public String bookMovieTicket(@RequestParam Long screenId, @RequestParam Long layoutCategoryId, @RequestParam Integer selectedSeatNumber) throws InterruptedException {
        return bookingService.bookMovieTicket(screenId,layoutCategoryId, selectedSeatNumber);
    }

    @PutMapping("/cancel-ticket")
    public String cancelMovieTicket(@RequestParam String transactionId){
        return bookingService.cancelMovieTicket(transactionId);
    }

}
