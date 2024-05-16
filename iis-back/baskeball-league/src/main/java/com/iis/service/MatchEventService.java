package com.iis.service;

import com.iis.dtos.MatchEventDto;

import java.util.List;

public interface MatchEventService {

    MatchEventDto AddEvent(MatchEventDto eventDto);
    List<MatchEventDto> GetAllByMatchId(long matchId);
}
