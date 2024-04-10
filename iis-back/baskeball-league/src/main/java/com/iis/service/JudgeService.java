package com.iis.service;

import com.iis.dto.judgeDTOs.JudgeDTO;
import org.springframework.stereotype.Service;

@Service
public interface JudgeService {

    JudgeDTO Register(JudgeDTO judgeDTO);
    JudgeDTO Update(JudgeDTO judgeDTO);
    JudgeDTO GetById(long id);
}
