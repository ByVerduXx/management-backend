package com.ofc.management.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "concerts")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Concert implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_concert")
    @EqualsAndHashCode.Exclude
    private Integer id;

    private String title;

    private String description;

    private String place;

    private LocalDateTime date;

    private LocalDateTime soundcheck;

    private String scores;

    @OneToMany(mappedBy = "concert", fetch = FetchType.LAZY)
    private List<MusicianConcert> musicians = Collections.emptyList();

    @OneToMany(mappedBy = "concert", fetch = FetchType.LAZY)
    private List<Rehersal> rehersals = Collections.emptyList();

    public Concert(String title, String description, LocalDateTime date, LocalDateTime soundcheck, String scores) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.soundcheck = soundcheck;
        this.scores = scores;
    }
}
