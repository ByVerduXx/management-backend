package com.ofc.management.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class MusicianConcertPK implements Serializable {

    @Column(name = "id_musician")
    private Integer musicianId;

    @Column(name = "id_concert")
    private Integer concertId;
}
