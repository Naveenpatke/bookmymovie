package com.ayu.bookmymovie.Service;

import com.ayu.bookmymovie.Model.LayoutCategory;
import com.ayu.bookmymovie.Model.ReservedSeats;
import com.ayu.bookmymovie.Repository.LayoutCategoryRepository;
import com.ayu.bookmymovie.Repository.ReservedSeatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.ayu.bookmymovie.Constants.BookMyMovieConstants.*;

@Service
public class BookingService {

    @Autowired
    ReservedSeatsRepository reservedSeatsRepository;

    @Autowired
    LayoutCategoryRepository layoutCategoryRepository;

    public String bookMovieTicket(Long screenId, Long layoutCategoryId, Integer selectedSeatNumber ) throws InterruptedException {
        Optional<ReservedSeats> reservedSeatsDetailsFromDB =
                reservedSeatsRepository.findBookingDetailsByScreenIdAndLayoutCategoryAndReservedSeatNumber(screenId, layoutCategoryId, selectedSeatNumber, CANCELLED);

        if (reservedSeatsDetailsFromDB.isPresent()){
            return SEAT_ALREADY_BOOKED;
        } else {
            Optional<LayoutCategory> layoutCategory = layoutCategoryRepository.findById(layoutCategoryId);
            layoutCategory.ifPresent(layoutCategoryDetails -> {
                layoutCategoryDetails.setTotalSeatsBooked(layoutCategoryDetails.getTotalSeatsBooked() + 1);
                layoutCategoryRepository.save(layoutCategoryDetails);
            });
            reservedSeatsRepository
                    .reserveSeatForGivenScreenIdAndLayoutCategoryIdAndSeatNumber(UUID.randomUUID().toString(), PENDING, selectedSeatNumber, layoutCategoryId, screenId);
            TimeUnit.SECONDS.sleep(20);
            reservedSeatsRepository
                    .updateBookingStatus( BOOKED, selectedSeatNumber, layoutCategoryId, screenId, CANCELLED);
            return TICKET_BOOKED_SUCCESSFULLY;
        }
    }

    public String cancelMovieTicket(String transactionId ) {
        Optional<ReservedSeats> reservedSeatsDetailsFromDB =
                reservedSeatsRepository.findByTransactionId(transactionId);

        if (reservedSeatsDetailsFromDB.isPresent()) {
            Integer seatNumberToBeCancelled = reservedSeatsDetailsFromDB.get().getReservedSeatNumber();
            Long layoutCategoryId = reservedSeatsDetailsFromDB.get().getLayoutCategory().getId();
            Long screenId = reservedSeatsDetailsFromDB.get().getScreen().getId();
            Optional<LayoutCategory> layoutCategory = layoutCategoryRepository.findById(layoutCategoryId);
            layoutCategory.ifPresent(layoutCategoryDetails -> {
                layoutCategoryDetails.setTotalSeatsBooked(layoutCategoryDetails.getTotalSeatsBooked() - 1);
                layoutCategoryRepository.save(layoutCategoryDetails);
            });
            reservedSeatsRepository
                    .updateBookingStatus( CANCELLED, seatNumberToBeCancelled, layoutCategoryId, screenId, CANCELLED);
            return TICKET_CANCELLED_SUCCESSFULLY;
        } else {
            return NO_BOOKING_FOUND_FOR_THE_GIVEN_DETAILS;
        }
    }
}
