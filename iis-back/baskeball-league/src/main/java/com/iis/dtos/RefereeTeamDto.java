package com.iis.dtos;

import lombok.Data;

@Data
public class RefereeTeamDto {

    private long matchId;
    private long mainRefereeId;
    private long secondRefereeId;
    private long thirdRefereeId;
    private long fourthRefereeId;
}
