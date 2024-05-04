package com.iis.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MatchDto {

    private int id;
    private TeamDto homeTeam;
    private TeamDto awayTeam;
    private int mainRefereeId;
    private int secondRefereeId;
    private int thirdRefereeId;
    private int fourthRefereeId;
    private LocalDateTime matchDay;
    private String city;
    private boolean isHighRisk;
    private RecordKeeperDto recordKeeper;
    private MatchRosterDto homeRoster;
    private MatchRosterDto awayRoster;
}
