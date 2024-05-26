package com.iis.service;

import com.iis.dtos.MatchDto;
import com.iis.dtos.PlayerMatchStatsDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PlayerMatchStatsService {
    MatchDto CreatePlayerMatchStats(MatchDto matchDto);
    List<PlayerMatchStatsDto> GetAllForTeamOnMatch(long matchId, long teamId);
    List<PlayerMatchStatsDto> GetStatsForActivePlayersOnMatch(long matchId, long homeRosterId, long awayRosterId);
    void UpdatePlayersTimePlayed(List<PlayerMatchStatsDto> playersStats);
}
