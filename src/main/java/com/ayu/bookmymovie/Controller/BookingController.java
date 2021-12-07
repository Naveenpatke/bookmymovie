package com.ayu.bookmymovie.Controller;

import com.ayu.bookmymovie.DAO.SeatBooking;
import com.ayu.bookmymovie.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    BookingService bookingService;

    @PostMapping("/reserve-ticket")
    public String bookMovieTicket(@RequestParam Long screenId, @RequestParam Long layoutCategoryId, @RequestParam Integer selectedSeatNumber) throws InterruptedException {
        return bookingService.bookMovieTicket(screenId,layoutCategoryId, selectedSeatNumber);
    }

    @PostMapping("/reserve-multiple-seats")
    public String bookMultipleMovieTicket(@RequestParam Long screenId, @RequestBody List<SeatBooking> seatBookingList) throws InterruptedException {
        return bookingService.bookMultipleMovieTicket(screenId, seatBookingList);
    }


        @PutMapping("/cancel-ticket")
    public String cancelMovieTicket(@RequestParam String transactionId, @RequestBody List<SeatBooking> seatBookingList){
        return bookingService.cancelMovieTicket(transactionId, seatBookingList);
    }

}
