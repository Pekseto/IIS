package com.iis.service;

import com.iis.dtos.RegisteredUserDto;
import com.iis.model.Coach;
import com.iis.model.Player;
import com.iis.model.TeamManager;
import com.iis.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    Player registerPlayer(Player player);
    Coach registerCoach(Coach coach);
    TeamManager registerTeamManager(TeamManager teamManager);
    RegisteredUserDto updateAdmin(RegisteredUserDto user);
    User getById(long id);
}
