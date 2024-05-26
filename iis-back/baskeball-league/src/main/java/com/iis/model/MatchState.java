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

    @Column(name="match_id", nullable = false)
    private long matchId;

    @Column(name="home_points", nullable = false)
    private int homePoints;
    @Column(name="away_points", nullable = false)
    private int awayPoints;

    @Column(name="first_half_timeouts_home", nullable = false)
    private int firstHalfTimeoutsHome;
    @Column(name="second_half_timeouts_home", nullable = false)
    private int secondHalfTimeoutsHome;
    @Column(name="first_half_timeouts_away", nullable = false)
    private int firstHalfTimeoutsAway;
    @Column(name="second_half_timeouts_away", nullable = false)
    private int secondHalfTimeoutsAway;

    @Column(name="first_quarter_fouls_home", nullable = false)
    private int firstQuarterFoulsHome;
    @Column(name="second_quarter_fouls_home", nullable = false)
    private int secondQuarterFoulsHome;
    @Column(name="third_quarter_fouls_home", nullable = false)
    private int thirdQuarterFoulsHome;
    @Column(name="fourth_quarter_fouls_home", nullable = false)
    private int fourthQuarterFoulsHome;
    @Column(name="first_quarter_fouls_away", nullable = false)
    private int firstQuarterFoulsAway;
    @Column(name="second_quarter_fouls_away", nullable = false)
    private int secondQuarterFoulsAway;
    @Column(name="third_quarter_fouls_away", nullable = false)
    private int thirdQuarterFoulsAway;
    @Column(name="fourth_quarter_fouls_away", nullable = false)
    private int fourthQuarterFoulsAway;

    @Column(nullable = false)
    private int minute;
    @Column(nullable = false)
    private int second;
    @Column(nullable = false)
    private int quarter;

    @Column(name = "is_finished", nullable = false)
    private boolean isFinished;
}
