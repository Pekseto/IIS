package com.iis.service.impl;

import com.iis.dtos.MatchDto;
import com.iis.dtos.PlayerMatchStatsDto;
import com.iis.model.Match;
import com.iis.model.Player;
import com.iis.model.PlayerMatchStats;
import com.iis.repository.PlayerMatchStatsRepository;
import com.iis.service.PlayerMatchStatsService;
import com.iis.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerMatchStatsServiceImpl implements PlayerMatchStatsService {
    private final PlayerMatchStatsRepository repo;
    private final Mapper mapper;

    @Override
    public MatchDto CreatePlayerMatchStats(MatchDto matchDto) {
        var match = mapper.map(matchDto, Match.class);
        var players = new ArrayList<Player>();

        players.addAll(match.getHomeRoster().getStartingFive());
        players.addAll(match.getHomeRoster().getBenchPlayers());
        players.addAll(match.getAwayRoster().getStartingFive());
        players.addAll(match.getAwayRoster().getBenchPlayers());

        players.forEach(player -> {

            var playerMatchStats = new PlayerMatchStats();

            playerMatchStats.setPlayerId(player.getId());
            playerMatchStats.setTeamId(player.getTeam().getId());
            playerMatchStats.setMatchId(match.getId());

            repo.save(playerMatchStats);
        });

        return matchDto;
    }

    @Override
    public List<PlayerMatchStatsDto> GetAllForTeamOnMatch(long matchId, long teamId) {
        var playersMatchStats = repo.getAllForTeamOnMatch(matchId, teamId);

        return playersMatchStats.stream()
                .map(playerMatchStats -> mapper.map(playerMatchStats, PlayerMatchStatsDto.class))
                .toList();
    }
}
