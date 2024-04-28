package com.iis.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MatchDto {

    private int id;
    private int homeTeamId;
    private String homeTeamName;
    private int awayTeamId;
    private String awayTeamName;
    private int mainRefereeId;
    private int secondRefereeId;
    private int thirdRefereeId;
    private int fourthRefereeId;
    private LocalDateTime matchDay;
    private String city;
    private boolean isHighRisk;
}
