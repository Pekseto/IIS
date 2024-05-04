package com.iis.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper=false)
@Data
@Entity
@Table(name="matches")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "home_team_id", nullable = false)
    private Team homeTeam;

    @ManyToOne
    @JoinColumn(name = "away_team_id", nullable = false)
    private Team awayTeam;

    @Column(name="mainRefereeId", nullable = true)
    private Long mainRefereeId;

    @Column(name="secondRefereeId", nullable = true)
    private Long secondRefereeId;

    @Column(name="thirdRefereeId", nullable = true)
    private Long thirdRefereeId;

    @Column(name="fourthRefereeId", nullable = true)
    private Long fourthRefereeId;

    @Column(name="matchDay", nullable = true)
    private LocalDateTime matchDay;

    @Column(name="city", nullable = true)
    private String city;

    @ManyToOne
    @JoinColumn(name = "record_keeper_id", nullable = true)
    private RecordKeeper recordKeeper;

    @ManyToOne
    @JoinColumn(name = "home_roster_id", nullable = true)
    private MatchRoster homeRoster;

    @ManyToOne
    @JoinColumn(name = "away_roster_id", nullable = true)
    private MatchRoster awayRoster;
}
