package com.iis.service.impl;

import com.iis.dtos.TeamDto;
import com.iis.model.Team;
import com.iis.repository.CoachRepository;
import com.iis.repository.PlayerRepository;
import com.iis.repository.TeamManagerRepository;
import com.iis.repository.TeamRepository;
import com.iis.service.TeamService;
import com.iis.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;
    private final CoachRepository coachRepository;
    private final TeamManagerRepository teamManagerRepository;
    private final Mapper mapper;

    @Override
    public Team register(TeamDto teamForRegistration) {
        Team team = mapper.map(teamForRegistration,Team.class);
        team.setPlayers(playerRepository.findAllById(teamForRegistration.getPlayerIds()));
        team.setTeamManager(teamManagerRepository.findById(teamForRegistration.getTeamManagerId()).orElse(null));
        team.setCoach(coachRepository.findById(teamForRegistration.getCoachId()).orElse(null));
        return teamRepository.save(team);
    }

    @Override
    public List<Team> getAll() {
        return teamRepository.findAll();
    }
}
