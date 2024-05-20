package com.iis.service.impl;

import com.iis.dtos.RecordKeeperDto;
import com.iis.dtos.MatchDto;
import com.iis.dtos.RefereeTeamDto;
import com.iis.helpers.SearchIn;
import com.iis.model.Match;
import com.iis.model.RegularSeasonSchedule;

import com.iis.repository.MatchRepository;
import com.iis.repository.RecordKeeperRepository;
import com.iis.repository.TeamRepository;
import com.iis.service.MatchService;
import com.iis.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MatchServiceImpl implements MatchService {

    private final MatchRepository matchRepository;
    private final RecordKeeperRepository recordKeeperRepository;
    private final Mapper mapper;
    private final TeamRepository teamRepository;
    private final ModelMapper modelMapper;

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
        var matchesDto = matchesFromDb.stream()
                .map(match -> mapper.map(match, MatchDto.class))
                .collect(Collectors.toList());

//        matchesDto.forEach(match -> {
//            long id = match.getHomeTeamId();
//            var teamFromDb = teamRepository.findById(id);
//            match.setHomeTeamName(teamFromDb.get().getName());
//
//            id = match.getAwayTeamId();
//            teamFromDb = teamRepository.findById(id);
//            match.setAwayTeamName(teamFromDb.get().getName());
//        } );

        return matchesDto;
    }

    @Override
    public MatchDto GetMatch(long id) {
        var matchFromDb = matchRepository.findById(id);
        var retVal = modelMapper.map(matchFromDb, MatchDto.class);

//        long teamId = matchFromDb.get().getHomeTeamId();
//        var team = teamRepository.findById(teamId);
//
//        retVal.setHomeTeamName(team.get().getName());
//        teamId = matchFromDb.get().getAwayTeamId();
//        team = teamRepository.findById(teamId);
//        retVal.setAwayTeamName(team.get().getName());

        return retVal;
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


    @Override
    public MatchDto GetById(long matchId) {
        var match = matchRepository.getReferenceById(matchId);
        return mapper.map(match, MatchDto.class);
    }

}
