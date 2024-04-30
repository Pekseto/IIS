package com.iis.service;

import com.iis.dtos.MatchRosterDto;

public interface MatchRosterService {

    MatchRosterDto AddToBench(long id, long playerId);
    MatchRosterDto RemoveFromBench(long id, long playerId);
    MatchRosterDto AddToStartingFive(long id, long playerId);
    MatchRosterDto RemoveFromStartingFive(long id, long playerId);
    MatchRosterDto Substitute(long id, long inId, long outId);
    MatchRosterDto GetMatchRoster(long id);
}
