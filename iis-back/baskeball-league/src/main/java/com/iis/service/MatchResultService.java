package com.iis.service;

import com.iis.dtos.MatchResultDto;
import com.iis.model.MatchResult;

import java.util.List;

public interface MatchResultService {
    MatchResultDto save(MatchResultDto matchResultDto);

    List<MatchResultDto> getAll();

    MatchResultDto getById(Long id);
}
