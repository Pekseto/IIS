package com.iis.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class PlayerMatchStatsDto {
    private Long id;
    private Long playerId;
    private Long matchId;

    private Integer MinutesPlayed;
    private Integer PlusMinus;
    private Integer PIR;
    // @Column(name = "player_efficiency_rating", nullable = false)
    // private Integer PER;

    private Integer TwoPM;
    private Integer ThreePM;
    private Integer FreeThrowM;

    private Integer TwoPA;
    private Integer ThreePA;
    private Integer FreeThrowA;

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
