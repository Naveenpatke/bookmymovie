package com.ayu.bookmymovie.Repository;

import com.ayu.bookmymovie.Model.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CinemaRepository extends JpaRepository<Cinema, Long> {
}