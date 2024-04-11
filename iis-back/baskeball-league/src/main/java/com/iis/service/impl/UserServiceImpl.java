package com.iis.service.impl;

import com.iis.model.Coach;
import com.iis.model.Player;
import com.iis.model.TeamManager;
import com.iis.repository.CoachRepository;
import com.iis.repository.PlayerRepository;
import com.iis.repository.TeamManagerRepository;
import com.iis.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PlayerRepository playerRepository;
    private final CoachRepository coachRepository;
    private final TeamManagerRepository teamManagerRepository;
    @Override
    public Player registerPlayer(Player player) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        // Hesiranje šifre
        player.setPassword(encoder.encode(player.getPassword()));

        return playerRepository.save(player);
    }

    @Override
    public Coach registerCoach(Coach coach) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        // Hesiranje šifre
        coach.setPassword(encoder.encode(coach.getPassword()));

        return coachRepository.save(coach);
    }

    @Override
    public TeamManager registerTeamManager(TeamManager teamManager) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        // Hesiranje šifre
        teamManager.setPassword(encoder.encode(teamManager.getPassword()));

        return teamManagerRepository.save(teamManager);
    }

}
