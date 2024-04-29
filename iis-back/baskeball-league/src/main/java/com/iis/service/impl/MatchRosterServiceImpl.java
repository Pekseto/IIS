package com.iis.service.impl;

import com.iis.dtos.MatchRosterDto;
import com.iis.model.MatchRoster;
import com.iis.repository.MatchRosterRepository;
import com.iis.repository.PlayerRepository;
import com.iis.service.MatchRosterService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MatchRosterServiceImpl implements MatchRosterService {
    private final MatchRosterRepository repo;
    private final PlayerRepository playerRepository;
    ModelMapper modelMapper = new ModelMapper();

    @Override
    public MatchRosterDto SetBenchPlayer() {
        var player = playerRepository.getReferenceById((long)2);
        var matchRoster = repo.getReferenceById((long)1);

        matchRoster.AddBenchPlayer(player);
        repo.save(matchRoster);

        return modelMapper.map(matchRoster, MatchRosterDto.class);
    }

    @Override
    public MatchRosterDto GetMatchRoster(long id) {
        var matchRoster = repo.getReferenceById(id);
        return modelMapper.map(matchRoster, MatchRosterDto.class);
    }
}
