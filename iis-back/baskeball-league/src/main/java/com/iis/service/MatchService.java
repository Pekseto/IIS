package com.iis.service;

import com.iis.dtos.MatchDto;
import com.iis.dtos.RefereeTeamDto;
import com.iis.helpers.SearchIn;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MatchService {

    RefereeTeamDto SetRefereeTeam(RefereeTeamDto refereeTeam);
    List<MatchDto> GetAll();
}
