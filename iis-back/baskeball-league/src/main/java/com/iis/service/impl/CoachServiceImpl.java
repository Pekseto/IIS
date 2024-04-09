package com.iis.service.impl;

import com.iis.dtos.RegisteredUserDto;
import com.iis.model.Coach;
import com.iis.model.Player;
import com.iis.repository.CoachRepository;
import com.iis.service.CoachService;
import com.iis.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CoachServiceImpl implements CoachService {

    private final CoachRepository coachRepository;
    private final Mapper mapper;

    @Override
    public RegisteredUserDto edit(RegisteredUserDto coach) {
        Coach coachToEdit = mapper.map(coach, Coach.class);;

        return mapper.map(coachRepository.save(coachToEdit), RegisteredUserDto.class);
    }
}
