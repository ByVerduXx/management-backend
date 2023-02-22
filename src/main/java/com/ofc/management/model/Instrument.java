package com.ofc.management.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "instruments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Instrument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_instrument")
    private Integer id;

    private String name;

    public Instrument(String name) {
        this.name = name;
    }
}
