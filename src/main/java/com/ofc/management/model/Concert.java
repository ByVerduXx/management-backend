package com.ofc.management.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "concerts")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Concert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_concert")
    @EqualsAndHashCode.Exclude
    private Integer id;

    private String title;

    private String description;

    private LocalDateTime date;

    private LocalDateTime soundcheck;

    private String scores;

    public Concert(String title, String description, LocalDateTime date, LocalDateTime soundcheck, String scores) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.soundcheck = soundcheck;
        this.scores = scores;
    }
}
