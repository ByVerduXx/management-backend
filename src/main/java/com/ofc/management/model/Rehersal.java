package com.ofc.management.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "rehearsals")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Rehersal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rehearsal")
    @EqualsAndHashCode.Exclude
    private Integer id;

    private LocalDateTime date;

    private String place;

    @ManyToOne
    @JoinColumn(name = "id_concert")
    private Concert concert;

}
