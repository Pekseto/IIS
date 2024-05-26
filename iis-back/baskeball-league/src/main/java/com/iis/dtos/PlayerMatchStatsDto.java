package com.iis.dtos;

import lombok.Data;

@Data
public class PlayerMatchStatsDto {
    private Long id;
    private PlayerDto player;
    private Long matchId;

    private Integer MinutesPlayed;
    private Integer SecondsPlayed;
    private Integer PlusMinus;
    private Integer PIR;
    // @Column(name = "player_efficiency_rating", nullable = false)
    // private Integer PER;

    private Integer Points;

    private Integer TwoPM;
    private Integer ThreePM;
    private Integer FreeThrowM;

    private Integer TwoPA;
    private Integer ThreePA;
    private Integer FreeThrowA;

    private Double TwoPP;
    private Double ThreePP;
    private Double FreeThrowP;

    private Integer OffRebounds;
    private Integer DefRebounds;

    private Integer Assists;
    private Integer Steals;
    private Integer Turnovers;

    private Integer Blocks;
    private Integer BlocksAgainst;

    private Integer FoulsCommited;
    private Integer FoulsDrawn;
}
