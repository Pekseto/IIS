package com.iis.dtos;

import lombok.Data;

import java.time.LocalTime;

@Data
public class MatchEventDto {
    private Long id;
    private String type;
    private LocalTime time;
    private long playerId;
    private long matchId;
    private int period;
}
