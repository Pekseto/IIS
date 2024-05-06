package com.iis.dtos;

import lombok.Data;

import java.time.LocalTime;

@Data
public class MatchEventDto {
    private Long id;
    private String type;
    private int minute;
    private int second;
    private String perpetratorName;
    private Long perpetratorId;
    private long matchId;
    private int period;
}
