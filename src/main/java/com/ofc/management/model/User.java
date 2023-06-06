package com.ofc.management.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;


import java.io.Serializable;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    @Column(name = "id_user")
    private Integer id;


    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_instrument")
    private Instrument instrument;

    public User(Integer id, String name, String lastName, String username, String password, String role) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User(String name, String lastName, String username, String password, String role) {
        this.name = name;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User(Integer id, String name, String lastName, String username, String password, Instrument instrument, String role) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.instrument = instrument;
        this.role = role;
    }

    public User(String name, String lastName, String username, String password, Instrument instrument, String role) {
        this.name = name;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.instrument = instrument;
        this.role = role;
    }

    public User(Integer id) {
        this.id = id;
    }
}
