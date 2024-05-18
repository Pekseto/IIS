package com.iis.service.impl;

import com.iis.dtos.RecordKeeperDto;
import com.iis.dtos.MatchDto;
import com.iis.dtos.RefereeTeamDto;
import com.iis.helpers.SearchIn;
import com.iis.model.Match;
import com.iis.model.RegularSeasonSchedule;
import com.iis.repository.MatchRepository;
import com.iis.repository.RecordKeeperRepository;
import com.iis.service.MatchService;
import com.iis.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MatchServiceImpl implements MatchService {

    private final MatchRepository matchRepository;
    private final RecordKeeperRepository recordKeeperRepository;
    private final Mapper mapper;

    @Override
    public RefereeTeamDto SetRefereeTeam(RefereeTeamDto refereeTeam) {

        var matchFromDb = matchRepository.getById(refereeTeam.getMatchId());

        matchFromDb.setMainRefereeId(refereeTeam.getMainRefereeId());
        matchFromDb.setSecondRefereeId(refereeTeam.getSecondRefereeId());
        matchFromDb.setThirdRefereeId(refereeTeam.getThirdRefereeId());
        matchFromDb.setFourthRefereeId(refereeTeam.getFourthRefereeId());

        matchRepository.save(matchFromDb);

        return refereeTeam;
    }

    @Override
    public List<MatchDto> GetAll() {
        var matchesFromDb = matchRepository.findAll();
        return matchesFromDb.stream()
                .map(match -> mapper.map(match, MatchDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public MatchDto DelegateRecordKeeper(long matchId, long recordKeeperId) {
        var match = matchRepository.getReferenceById(matchId);
        var recordKeeper = recordKeeperRepository.getReferenceById(recordKeeperId);

        match.setRecordKeeper(recordKeeper);
        matchRepository.save(match);

        return mapper.map(match, MatchDto.class);
    }

    @Override
    public MatchDto Save(MatchDto matchDto) {
        matchRepository.save(mapper.map(matchDto, Match.class));
        return matchDto;
    }


}
