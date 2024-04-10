package com.iis.service.impl;

import com.iis.dto.judgeDTOs.RefereeDTO;
import com.iis.model.Referee;
import com.iis.repository.RefereeRepository;
import com.iis.service.RefereeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefereeServiceImpl implements RefereeService {

    private final RefereeRepository judgeRepository;
    ModelMapper modelMapper = new ModelMapper();

    @Override
    public RefereeDTO Register(RefereeDTO judgeDTO) {
        var retVal = judgeRepository.save(modelMapper.map(judgeDTO, Referee.class));
        return modelMapper.map(retVal, RefereeDTO.class);
    }

    @Override
    public RefereeDTO Update(RefereeDTO judgeDTO) {
        var retVal = judgeRepository.save(modelMapper.map(judgeDTO, Referee.class));
        return modelMapper.map(retVal, RefereeDTO.class);
    }

    @Override
    public RefereeDTO GetById(long id) {
        return null;
        //var retVal = judgeRepository.findBy(id);
        //return modelMapper.map(retVal, JudgeDTO.class);
    }
}
