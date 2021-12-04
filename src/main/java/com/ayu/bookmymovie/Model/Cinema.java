package com.ayu.bookmymovie.Model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "cinema")
public class Cinema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cinema_id", nullable = false)
    private Long cinema_id;

    private String cinema_name;

    private String address;

    private String rating;

    @OneToMany(mappedBy = "cinema")
    private List<Screen> screens;
}