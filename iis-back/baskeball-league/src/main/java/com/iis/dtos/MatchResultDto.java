package com.iis.dtos;

import lombok.Data;

@Data
public class MatchResultDto {
    private Long id;
    private MatchDto match;
    private int homeTeamScore;
    private int awayTeamScore;
}
