package com.iis.dtos;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TeamDto {

    private Long id;

    private String name;

    private String address;

    private String email;

    private String phoneNumber;

    private String city;

    private String country;

    private List<PlayerDto> players;

    private List<Long> playerIds = new ArrayList<>();

    private Long teamManagerId;

    private Long coachId;
}
