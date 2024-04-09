package com.iis.service;

import com.iis.model.Coach;
import com.iis.model.Player;
import com.iis.model.TeamManager;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    Player registerPlayer(Player player);
    Coach registerCoach(Coach coach);
    TeamManager registerTeamManager(TeamManager teamManager);
}
