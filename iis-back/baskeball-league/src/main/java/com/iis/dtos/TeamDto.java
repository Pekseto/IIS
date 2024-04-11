package com.iis.dtos;

import com.iis.model.Coach;
import com.iis.model.Player;
import com.iis.model.TeamManager;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TeamDto {

    private String name;

    private String email;

    private String address;

    private String phoneNumber;

    private String city;

    private String country;

    private List<Long> playerIds = new ArrayList<>();

    private Long teamManagerId;

    private Long coachId;
}
