package com.iis.service;

import com.iis.dtos.MatchDto;
import com.iis.dtos.PlayerMatchStatsDto;
import com.lowagie.text.DocumentException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface PlayerMatchStatsService {
    MatchDto CreatePlayerMatchStats(MatchDto matchDto);
    List<PlayerMatchStatsDto> GetAllForTeamOnMatch(long matchId, long teamId);
    List<PlayerMatchStatsDto> GetStatsForActivePlayersOnMatch(long matchId, long homeRosterId, long awayRosterId);
    void UpdatePlayersTimePlayed(List<PlayerMatchStatsDto> playersStats);
    byte[] exportPdf(Long matchId) throws IOException, DocumentException;
}
