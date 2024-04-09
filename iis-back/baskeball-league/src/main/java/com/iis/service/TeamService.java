package com.iis.service;

import com.iis.model.Team;
import org.springframework.stereotype.Service;

@Service
public interface TeamService {
    Team register(Team teamForRegistration);
}
