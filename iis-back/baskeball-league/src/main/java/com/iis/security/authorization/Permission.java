package com.iis.security.authorization;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Permission {

    LEAGUE_ADMIN_READ("leagueAdmin:read"),
    LEAGUE_ADMIN_UPDATE("leagueAdmin:update"),
    LEAGUE_ADMIN_DELETE("leagueAdmin:delete"),
    LEAGUE_ADMIN_CREATE("leagueAdmin:create"),
    PLAYER_READ("player:read"),
    PLAYER_UPDATE("player:update"),
    COACH_READ("coach:read"),
    COACH_UPDATE("coach:update"),
    TEAM_MANAGER_READ("teamManager:read"),
    TEAM_MANAGER_UPDATE("teamManager:update")
    ;

    private final String permission;
}
