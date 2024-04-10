package com.iis.service;

import com.iis.model.Team;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TeamService {
    Team register(Team teamForRegistration);
    List<Team> getAll();
}
