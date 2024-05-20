package com.iis.service;

import com.iis.dtos.MatchStateDto;
import org.springframework.stereotype.Service;

@Service
public interface MatchStateService {
    MatchStateDto AddMatchState(MatchStateDto matchStateDto);
    MatchStateDto GetByMatchId(long matchId);
}
