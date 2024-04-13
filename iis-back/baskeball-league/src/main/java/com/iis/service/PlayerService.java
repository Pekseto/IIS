package com.iis.service;

import com.iis.dtos.RegisteredUserDto;
import com.iis.model.Player;

public interface PlayerService {
    RegisteredUserDto edit(RegisteredUserDto player);

    Player getById(long id);
}
