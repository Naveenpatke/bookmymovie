package com.ayu.bookmymovie.Repository;

import com.ayu.bookmymovie.Model.Screen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScreenRepository extends JpaRepository<Screen, Long> {
}