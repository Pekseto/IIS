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
        Team team = mapper.map(teamForRegistration, Team.class);
        
        if (teamForRegistration.getPlayerIds() != null && !teamForRegistration.getPlayerIds().isEmpty()) {
            team.setPlayers(playerRepository.findAllById(teamForRegistration.getPlayerIds()));
        } else {
            team.setPlayers(null); // ili new ArrayList<>()
        }

        if (teamForRegistration.getTeamManagerId() != null) {
            team.setTeamManager(teamManagerRepository.findById(teamForRegistration.getTeamManagerId()).orElse(null));
        } else {
            // Ako je id menadžera null, postavite ga na null
            team.setTeamManager(null);
        }

        if (teamForRegistration.getCoachId() != null) {
            team.setCoach(coachRepository.findById(teamForRegistration.getCoachId()).orElse(null));
        } else {
            team.setCoach(null);
        }

        return teamRepository.save(team);
    }


    @Override
    public List<Team> getAll() {
        return teamRepository.findAll();
    }

    @Override
    public Team getById(Long id) {
        return teamRepository.getById(id);
    }
}
