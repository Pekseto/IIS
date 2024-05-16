package com.iis.dtos;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class MatchStateDto {
    private Long id;

    private long matchId;
    private Long winningTeamId;

    private int homePoints;
    private int awayPoints;

    private int firstHalfTimeoutsHome;
    private int secondHalfTimeoutsHome;
    private int firstHalfTimeoutsAway;
    private int secondHalfTimeoutsAway;

    private int firstQuarterFoulsHome;
    private int secondQuarterFoulsHome;
    private int thirdQuarterFoulsHome;
    private int fourthQuarterFoulsHome;
    private int firstQuarterFoulsAway;
    private int secondQuarterFoulsAway;
    private int thirdQuarterFoulsAway;
    private int fourthQuarterFoulsAway;

    private int minute;
    private int second;
    private int quarter;
}
