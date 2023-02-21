package com.ofc.management.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "musicians_concerts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MusicianConcert {

    @EmbeddedId
    private MusicianConcertPK id;

    @ManyToOne
    @MapsId("musicianId")
    @JoinColumn(name = "id_musician")
    private User user;

    @ManyToOne
    @MapsId("concertId")
    @JoinColumn(name = "id_concert")
    private Concert concert;

    private String role;

    private BigDecimal payment;

    private boolean accepted;

    private boolean pending;
}
