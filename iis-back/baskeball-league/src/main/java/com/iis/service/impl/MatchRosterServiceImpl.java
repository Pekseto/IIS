package com.iis.service.impl;

import com.iis.dtos.MatchRosterDto;
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
    private final ModelMapper modelMapper;

    @Override
    public MatchRosterDto AddToBench(long id, long playerId) {
        var matchRoster = repo.getReferenceById(id);
        var player = playerRepository.getReferenceById(playerId);

        matchRoster.AddToBench(player);

        repo.save(matchRoster);

        return modelMapper.map(matchRoster, MatchRosterDto.class);
    }

    @Override
    public MatchRosterDto RemoveFromBench(long id, long playerId) {
        var matchRoster = repo.getReferenceById(id);
        var player = playerRepository.getReferenceById(playerId);

        matchRoster.RemoveFromBench(player);

        repo.save(matchRoster);

        return modelMapper.map(matchRoster, MatchRosterDto.class);
    }

    @Override
    public MatchRosterDto AddToStartingFive(long id, long playerId) {
        var matchRoster = repo.getReferenceById(id);
        var player = playerRepository.getReferenceById(playerId);

        matchRoster.RemoveFromBench(player);
        matchRoster.AddToStartingFive(player);
        matchRoster.AddToActiveFive(player);

        repo.save(matchRoster);

        return modelMapper.map(matchRoster, MatchRosterDto.class);
    }

    @Override
    public MatchRosterDto RemoveFromStartingFive(long id, long playerId) {
        var matchRoster = repo.getReferenceById(id);
        var player = playerRepository.getReferenceById(playerId);

        matchRoster.AddToBench(player);
        matchRoster.RemoveFromStartingFive(player);
        matchRoster.RemoveFromActiveFive(player);

        repo.save(matchRoster);

        return modelMapper.map(matchRoster, MatchRosterDto.class);
    }

    @Override
    public MatchRosterDto Substitute(long id, long inId, long outId) {
        var matchRoster = repo.getReferenceById(id);
        var playerIn = playerRepository.getReferenceById(inId);
        var playerOut = playerRepository.getReferenceById(outId);

        matchRoster.AddToActiveFive(playerIn);
        matchRoster.AddToBench(playerOut);
        matchRoster.RemoveFromActiveFive(playerOut);
        matchRoster.RemoveFromBench(playerIn);

        repo.save(matchRoster);

        return modelMapper.map(matchRoster, MatchRosterDto.class);
    }

    @Override
    public MatchRosterDto GetMatchRoster(long id) {
        var matchRoster = repo.getReferenceById(id);
        return modelMapper.map(matchRoster, MatchRosterDto.class);
    }
}
