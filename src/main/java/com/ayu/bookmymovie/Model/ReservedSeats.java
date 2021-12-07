package com.ayu.bookmymovie.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "reserved_seats")
public class ReservedSeats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String transactionId;

    private String bookingStatus;

    private Integer reservedSeatNumber;

    @ManyToOne
    @JoinColumn(name = "layout_category_id")
    @JsonIgnore
    private LayoutCategory layoutCategory;

    @ManyToOne
    @JoinColumn(name = "screen_id")
    @JsonIgnore
    private Screen screen;

}