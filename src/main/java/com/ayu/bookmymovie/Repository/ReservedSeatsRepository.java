package com.ayu.bookmymovie.Repository;

import com.ayu.bookmymovie.Model.ReservedSeats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

public interface ReservedSeatsRepository extends JpaRepository<ReservedSeats, Long> {

    @Query(value = "select * from reserved_seats r where r.SCREEN_ID= ?1 AND r.LAYOUT_CATEGORY_ID = ?2 AND r.RESERVED_SEAT_NUMBER = ?3 AND r.BOOKING_STATUS != ?4", nativeQuery = true)
    Optional<ReservedSeats> findBookingDetailsByScreenIdAndLayoutCategoryAndReservedSeatNumber(Long screenId, Long layoutCategoryId, Integer selectedSeatNumber, String cancelledBookingStatus);

    @Modifying
    @Query(value = "insert into reserved_seats (TRANSACTION_ID, BOOKING_STATUS, RESERVED_SEAT_NUMBER, LAYOUT_CATEGORY_ID,SCREEN_ID) values (?1,?2,?3,?4,?5 )",
            nativeQuery = true)
    @Transactional
    void reserveSeatForGivenScreenIdAndLayoutCategoryIdAndSeatNumber(String transactionID, String bookingStatus, Integer reservedSeatNumber, Long layoutCategoryId, Long screenId);

    @Modifying
    @Query(value = "update reserved_seats r set r.BOOKING_STATUS = ?1 where r.RESERVED_SEAT_NUMBER = ?2 AND r.LAYOUT_CATEGORY_ID = ?3 AND  r.SCREEN_ID = ?4 AND r.BOOKING_STATUS != ?5", nativeQuery = true)
    @Transactional
    void updateBookingStatus(String bookingStatus, Integer reservedSeatNumber, Long layoutCategoryId, Long screenId, String cancelledBookingStatus);

    Optional<ReservedSeats> findByTransactionId(String transactionId);
}