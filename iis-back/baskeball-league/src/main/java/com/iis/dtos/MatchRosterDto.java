package com.iis.dtos;

import lombok.Data;

import java.util.List;

@Data
public class MatchRosterDto {
    private int id;

    private List<RegisteredUserDto> benchPlayers;
    private List<RegisteredUserDto> startingFive;
    private List<RegisteredUserDto> activeFive;

}
