package com.iis.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=false)
@Data
@Entity
@Table(name="match_states")
public class MatchState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique = true, nullable = false)
    private Long id;

    @Column(nullable = false)
    private long matchId;
    @Column(nullable = true)
    private Long winningTeamId;

    @Column(nullable = false)
    private int homePoints;
    @Column(nullable = false)
    private int awayPoints;

    @Column(nullable = false)
    private int firstHalfTimeoutsHome;
    @Column(nullable = false)
    private int secondHalfTimeoutsHome;
    @Column(nullable = false)
    private int firstHalfTimeoutsAway;
    @Column(nullable = false)
    private int secondHalfTimeoutsAway;

    @Column(nullable = false)
    private int firstQuarterFoulsHome;
    @Column(nullable = false)
    private int secondQuarterFoulsHome;
    @Column(nullable = false)
    private int thirdQuarterFoulsHome;
    @Column(nullable = false)
    private int fourthQuarterFoulsHome;
    @Column(nullable = false)
    private int firstQuarterFoulsAway;
    @Column(nullable = false)
    private int secondQuarterFoulsAway;
    @Column(nullable = false)
    private int thirdQuarterFoulsAway;
    @Column(nullable = false)
    private int fourthQuarterFoulsAway;

    @Column(nullable = false)
    private int minute;
    @Column(nullable = false)
    private int second;
    @Column(nullable = false)
    private int quarter;
}
