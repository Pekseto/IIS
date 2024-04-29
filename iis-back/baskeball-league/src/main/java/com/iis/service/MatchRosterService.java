package com.iis.service;

import com.iis.dtos.MatchRosterDto;

public interface MatchRosterService {

    MatchRosterDto SetBenchPlayer();
    MatchRosterDto GetMatchRoster(long id);
}
