package com.iis.service;

import com.iis.dtos.RegisteredUserDto;
import com.iis.model.TeamManager;

public interface TeamManagerService {
    RegisteredUserDto edit(RegisteredUserDto manager);
}
