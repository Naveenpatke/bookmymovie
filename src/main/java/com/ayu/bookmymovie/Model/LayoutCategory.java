package com.ayu.bookmymovie.Model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "layout_category")
public class LayoutCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String categoryName;

    private Double price;

    @ManyToOne
    @JoinColumn(name = "layout_layout_id")
    private Layout layout;

//    @ElementCollection
//    private List<Integer> seatsBooked;
}