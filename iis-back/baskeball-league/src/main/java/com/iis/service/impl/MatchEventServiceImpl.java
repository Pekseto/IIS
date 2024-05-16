package com.iis.service.impl;

import com.iis.dtos.MatchEventDto;
import com.iis.model.MatchEvent;
import com.iis.repository.MatchEventRepository;
import com.iis.service.MatchEventService;
import com.iis.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MatchEventServiceImpl implements MatchEventService {
    private final MatchEventRepository repo;
    private final ModelMapper mapper;
    private final Mapper customMapper;

    @Override
    public MatchEventDto AddEvent(MatchEventDto eventDto) {
        var event = mapper.map(eventDto, MatchEvent.class);
        event = repo.save(event);
        eventDto = customMapper.mapMatchEventToDto(event);

        return eventDto;
    }

    @Override
    public List<MatchEventDto> GetAllByMatchId(long matchId) {
        var matchEvents = repo.getAllByMatchId(matchId);
        return matchEvents.stream()
                .map(customMapper::mapMatchEventToDto)
                .collect(Collectors.toList());
    }
}
