package com.ofc.management.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class User {

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

    @Column(name = "position")
    private String position;

    @PrimaryKeyJoinColumn(name = "id_instrument")
    @OneToOne(cascade = CascadeType.ALL)
    private Instrument instrument;

    @OneToMany(mappedBy = "user")
    List<Notification> notifications;

    @OneToMany(mappedBy = "user")
    List<Announcement> announcements;

    // list of user's concerts (as a musician)
    @OneToMany(mappedBy = "user")
    List<MusicianConcert> concerts;

    public User(Integer id, String name, String lastName, String username, String password, String position) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.position = position;
    }

    public User(String name, String lastName, String username, String password, String position) {
        this.name = name;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.position = position;
    }

    public User(Integer id, String name, String lastName, String username, String password, Instrument instrument) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.instrument = instrument;
    }

    public User(String name, String lastName, String username, String password, Instrument instrument) {
        this.name = name;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.instrument = instrument;
    }
}
