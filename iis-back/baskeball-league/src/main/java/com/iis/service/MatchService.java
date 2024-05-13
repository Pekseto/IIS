package com.iis.service;

import com.iis.dtos.MatchDto;
import com.iis.dtos.RefereeTeamDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MatchService {

    RefereeTeamDto SetRefereeTeam(RefereeTeamDto refereeTeam);
    List<MatchDto> GetAll();
    MatchDto DelegateRecordKeeper(long matchId, long recordKeeperId);
    MatchDto GetById(long matchId);
    MatchDto GetMatch(long id);
}
