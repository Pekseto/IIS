package com.iis.service;

import com.iis.dtos.RegisteredUserDto;
import com.iis.model.Coach;

public interface CoachService {
    RegisteredUserDto edit(RegisteredUserDto coach);

    Coach getById(long id);
}
