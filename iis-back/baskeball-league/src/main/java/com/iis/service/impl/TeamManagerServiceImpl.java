package com.iis.service.impl;

import com.iis.dtos.RegisteredUserDto;
import com.iis.model.TeamManager;
import com.iis.repository.TeamManagerRepository;
import com.iis.service.TeamManagerService;
import com.iis.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamManagerServiceImpl implements TeamManagerService {

    private final TeamManagerRepository teamManagerRepository;
    private final Mapper mapper;
    @Override
    public RegisteredUserDto edit(RegisteredUserDto manager) {
        TeamManager teamManagerToEdit = mapper.map(manager,TeamManager.class);

        return mapper.map(teamManagerRepository.save(teamManagerToEdit),RegisteredUserDto.class);
    }

    @Override
    public TeamManager getById(long id) {
        return teamManagerRepository.getReferenceById(id);
    }
}
