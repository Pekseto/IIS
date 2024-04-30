package com.iis.service.impl;

import com.iis.dtos.MatchEventDto;
import com.iis.model.MatchEvent;
import com.iis.repository.MatchEventRepository;
import com.iis.service.MatchEventService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MatchEventServiceImpl implements MatchEventService {
    private final MatchEventRepository repo;
    private final ModelMapper mapper;

    @Override
    public MatchEventDto AddEvent(MatchEventDto eventDto) {
        var event = mapper.map(eventDto, MatchEvent.class);

        event = repo.save(event);

        return mapper.map(event, MatchEventDto.class);
    }
}
