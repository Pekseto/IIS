package com.iis.service;

import com.iis.dtos.TeamDto;
import com.iis.model.Team;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TeamService {
    Team register(TeamDto teamForRegistration);
    List<Team> getAll();
}
