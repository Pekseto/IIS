package com.iis.service.impl;

import com.iis.dtos.MatchResultDto;
import com.iis.model.MatchResult;
import com.iis.repository.MatchResultRepository;
import com.iis.service.MatchResultService;
import com.iis.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchResultServiceImpl implements MatchResultService {

    private final MatchResultRepository matchResultRepository;
    private final Mapper mapper;
    @Override
    public MatchResultDto save(MatchResultDto matchResultDto) {
        MatchResult savedResult = matchResultRepository.save(mapper.map(matchResultDto, MatchResult.class));
        return mapper.map(savedResult, MatchResultDto.class);
    }

    @Override
    public List<MatchResultDto> getAll() {
        return mapper.mapList(matchResultRepository.findAll(),MatchResultDto.class);
    }

    @Override
    public MatchResultDto getById(Long id) {
        return mapper.map(matchResultRepository.getById(id), MatchResultDto.class);
    }
}
