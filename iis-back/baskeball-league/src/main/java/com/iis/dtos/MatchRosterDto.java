package com.iis.dtos;

import lombok.Data;

import java.util.List;

@Data
public class MatchRosterDto {
    private int id;

    private List<PlayerDto> benchPlayers;
    private List<PlayerDto> startingFive;
    private List<PlayerDto> activeFive;

}
