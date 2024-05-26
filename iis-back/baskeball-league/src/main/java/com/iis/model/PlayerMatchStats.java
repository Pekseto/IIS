package com.iis.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=false)
@Data
@Entity
@Table(name="player_match_stats")
public class PlayerMatchStats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique = true, nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "player_id")
    private Player player;
    @Column(name = "team_id", nullable = false)
    private long teamId;
    @Column(name = "match_id", nullable = false)
    private long matchId;

    @Column(name = "minutes_played")
    private Integer MinutesPlayed = 0;
    @Column(name = "seconds_played")
    private Integer SecondsPlayed = 0;
    @Column(name = "plus_minus")
    private Integer PlusMinus = 0;
    @Column(name = "player_index_rating")
    private Integer PIR = 0;
    // @Column(name = "player_efficiency_rating", nullable = false)
    // private Integer PER;

    @Column()
    private Integer Points = 0;

    @Column(name = "two_pm")
    private Integer TwoPM = 0;
    @Column(name = "three_pm")
    private Integer ThreePM = 0;
    @Column(name = "free_throw_m")
    private Integer FreeThrowM = 0;

    @Column(name = "two_pa")
    private Integer TwoPA = 0;
    @Column(name = "three_pa")
    private Integer ThreePA = 0;
    @Column(name = "free_throw_a")
    private Integer FreeThrowA = 0;

    @Column(name = "two_pp")
    private Double TwoPP = 0.0;
    @Column(name = "three_pp")
    private Double ThreePP = 0.0;
    @Column(name = "free_throw_p")
    private Double FreeThrowP = 0.0;

    @Column(name = "offensive_rebounds")
    private Integer OffRebounds = 0;
    @Column(name = "defensive_rebounds")
    private Integer DefRebounds = 0;

    @Column()
    private Integer Assists = 0;
    @Column()
    private Integer Steals = 0;
    @Column()
    private Integer Turnovers = 0;

    @Column()
    private Integer Blocks = 0;
    @Column(name = "blocks_against")
    private Integer BlocksAgainst = 0;

    @Column(name = "fouls_commited")
    private Integer FoulsCommited = 0;
    @Column(name = "fouls_drawn")
    private Integer FoulsDrawn = 0;
}
