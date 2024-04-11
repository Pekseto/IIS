package com.iis.service;

import com.iis.dto.judgeDTOs.RefereeDTO;
import org.springframework.stereotype.Service;

@Service
public interface RefereeService {

    RefereeDTO Register(RefereeDTO judgeDTO);
    RefereeDTO Update(RefereeDTO judgeDTO);
    RefereeDTO GetById(long id);
}
