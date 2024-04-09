package com.iis.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique = true, nullable = false)
    private Long id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="city", nullable = false)
    private String city;

    @Column(name="country", nullable = false)
    private String password;

    @Column(name="address", nullable = false)
    private String address;

    @Column(name="email", nullable = false)
    private String email;

    @Column(name="phone_number", nullable = false)
    private String phoneNumber;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private List<Player> players = new ArrayList<>();

    @OneToOne(mappedBy = "team")
    private TeamManager teamManager;

    @OneToOne(mappedBy = "team")
    private Coach coach;
}
