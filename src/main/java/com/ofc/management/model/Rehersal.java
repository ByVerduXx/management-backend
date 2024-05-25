package com.ofc.management.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "rehersals")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Rehersal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rehersal")
    @EqualsAndHashCode.Exclude
    private Integer id;

    private LocalDateTime date;

    private String place;

    @ManyToOne
    @JoinColumn(name = "id_concert")
    private Concert concert;

}
