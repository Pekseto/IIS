package com.iis.service.impl;

import com.iis.dtos.RegisteredUserDto;
import com.iis.model.Player;
import com.iis.repository.PlayerRepository;
import com.iis.service.PlayerService;
import com.iis.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final Mapper mapper;
    @Override
    public RegisteredUserDto edit(RegisteredUserDto player) {
        Player playerToEdit = mapper.map(player, Player.class);;

        return mapper.map(playerRepository.save(playerToEdit), RegisteredUserDto.class);
    }
}
