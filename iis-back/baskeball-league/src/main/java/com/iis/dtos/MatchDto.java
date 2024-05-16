package com.iis.dtos;

import com.iis.model.Team;
import lombok.Data;

import java.time.LocalDate;
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
    private LocalDate matchDay;
    private String city;
    private boolean isHighRisk;
    private RecordKeeperDto recordKeeper;
    private MatchRosterDto homeRoster;
    private MatchRosterDto awayRoster;

    public MatchDto(TeamDto homeTeam, TeamDto awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    @Override
    public String toString() {
        return "Home Team: " + homeTeam.getName() + ", Away Team: " + awayTeam.getName() + ", Match day: " + matchDay;
    }
}
