package com.iis.service.impl;

import com.iis.dtos.MatchStateDto;
import com.iis.model.MatchState;
import com.iis.repository.MatchRepository;
import com.iis.repository.MatchStateRepository;
import com.iis.service.MatchStateService;
import com.iis.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MatchStateServiceImpl implements MatchStateService {

    private final MatchStateRepository repo;
    private final MatchRepository repoMatch;
    private final Mapper mapper;

    @Override
    public MatchStateDto AddMatchState(MatchStateDto matchStateDto) {
        var matchId = matchStateDto.getMatchId();
        var matchFromDb = repoMatch.getById(matchId);
        matchFromDb.setIsFinished(true);
        repoMatch.save(matchFromDb);
        var matchState = mapper.map(matchStateDto, MatchState.class);
        repo.save(matchState);
        return matchStateDto;
    }

    @Override
    public MatchStateDto GetByMatchId(long matchId) {
        var matchState = repo.getByMatchId(matchId);
        return mapper.map(matchState, MatchStateDto.class);
    }
}
