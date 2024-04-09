package com.iis.service.impl;

import com.iis.model.Team;
import com.iis.repository.TeamRepository;
import com.iis.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;

    @Override
    public Team register(Team teamForRegistration) {
        return teamRepository.save(teamForRegistration);
    }
}
