package com.ayu.bookmymovie.Service;

import com.ayu.bookmymovie.DAO.SeatBooking;
import com.ayu.bookmymovie.Model.ReservedSeats;
import com.ayu.bookmymovie.Repository.ReservedSeatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.ayu.bookmymovie.Constants.BookMyMovieConstants.*;

@Service
public class BookingService {

    @Autowired
    ReservedSeatsRepository reservedSeatsRepository;

    public String cancelMovieTicket(String transactionId, List<SeatBooking> seatBookingList) {

        for (SeatBooking seatBookingObject : seatBookingList) {
            Integer seatNumberToBeCancelled = seatBookingObject.getSelectedSeatNumber();
            Long layoutCategoryId = seatBookingObject.getLayoutCategoryId();
            reservedSeatsRepository
                    .cancelTicketBasedOnTransactionIdAndLayoutCategoryIdAndReservedSeatNumber(CANCELLED, seatNumberToBeCancelled, layoutCategoryId, transactionId, CANCELLED);
        }

        return seatBookingList.size() > 1 ? TICKETS_CANCELLED_SUCCESSFULLY : TICKET_CANCELLED_SUCCESSFULLY;
    }

    public String bookMultipleMovieTicket(Long screenId, List<SeatBooking> seatBookingList) throws InterruptedException {
        UUID uuid = UUID.randomUUID();
        for (SeatBooking seatBookingObject : seatBookingList) {
            Integer selectedSeatNumber = seatBookingObject.getSelectedSeatNumber();
            Long layoutCategoryId = seatBookingObject.getLayoutCategoryId();
            Optional<ReservedSeats> reservedSeatsDetailsFromDB =
                    reservedSeatsRepository.findBookingDetailsByScreenIdAndLayoutCategoryAndReservedSeatNumber(screenId, layoutCategoryId, selectedSeatNumber, CANCELLED);
            if (reservedSeatsDetailsFromDB.isPresent()) {
                reservedSeatsRepository
                        .cancelTicketBasedOnTransactionId(CANCELLED, uuid.toString());
                return SEAT_ALREADY_BOOKED;
            }
            reservedSeatsRepository
                    .reserveSeatForGivenScreenIdAndLayoutCategoryIdAndSeatNumber(uuid.toString(), PENDING, selectedSeatNumber, layoutCategoryId, screenId);
        }
        TimeUnit.SECONDS.sleep(20);
        for (SeatBooking seatBookingObject : seatBookingList) {
            reservedSeatsRepository
                    .updateBookingStatus(BOOKED, seatBookingObject.getSelectedSeatNumber(), seatBookingObject.getLayoutCategoryId(), screenId, CANCELLED);
        }
        return seatBookingList.size() > 1 ? TICKETS_BOOKED_SUCCESSFULLY : TICKET_BOOKED_SUCCESSFULLY;
    }
}
