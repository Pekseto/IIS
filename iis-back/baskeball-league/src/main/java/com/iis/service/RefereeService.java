package com.iis.service;

import com.iis.dto.judgeDTOs.RefereeDTO;
import com.iis.helpers.SearchIn;
import com.iis.model.Referee;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface RefereeService {

    RefereeDTO Register(RefereeDTO judgeDTO);
    RefereeDTO Update(RefereeDTO judgeDTO);
    RefereeDTO GetById(long id);
    Page<Referee> GetAll(SearchIn dataIn);
}
