package com.iis.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name="players")
public class Player extends User{

    @Column(name="height", nullable = false)
    private double height;

    @Column(name="weight", nullable = false)
    private double weight;

    @Column(name="status", nullable = false)
    private PlayerStatus status;

    @Column(name="jersey_number", nullable = false)
    private int jerseyNumber;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
}
