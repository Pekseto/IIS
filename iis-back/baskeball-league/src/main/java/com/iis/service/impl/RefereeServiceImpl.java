package com.iis.service.impl;

import com.iis.dto.judgeDTOs.RefereeDTO;
import com.iis.model.Referee;
import com.iis.model.Role;
import com.iis.repository.RefereeRepository;
import com.iis.service.RefereeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RefereeServiceImpl implements RefereeService {

    private final RefereeRepository judgeRepository;
    ModelMapper modelMapper = new ModelMapper();

    @Override
    public RefereeDTO Register(RefereeDTO judgeDTO) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        var model = modelMapper.map(judgeDTO, Referee.class);
        model.setRole(Role.REFEREE);
        model.setPassword(encoder.encode(judgeDTO.getPassword()));
        var retVal = judgeRepository.save(model);
        return modelMapper.map(retVal, RefereeDTO.class);
    }

    @Override
    public RefereeDTO Update(RefereeDTO judgeDTO) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        var refereeFromDb = judgeRepository.getById(judgeDTO.getId());

        refereeFromDb.setName(judgeDTO.getName());
        refereeFromDb.setSurname(judgeDTO.getSurname());
        refereeFromDb.setCountry(judgeDTO.getCountry());
        refereeFromDb.setCity(judgeDTO.getCity());
        refereeFromDb.setJmbg(judgeDTO.getJmbg());
        refereeFromDb.setPhoneNumber(judgeDTO.getPhoneNumber());
        refereeFromDb.setBirthday(judgeDTO.getBirthday());
        refereeFromDb.setEmail(judgeDTO.getEmail());
        refereeFromDb.setPassword(encoder.encode(judgeDTO.getPassword()));
        refereeFromDb.setRole(judgeDTO.getRole());

        var retVal = judgeRepository.save(refereeFromDb);
        return modelMapper.map(retVal, RefereeDTO.class);
    }

    @Override
    public RefereeDTO GetById(long id) {
        var retVal = judgeRepository.getById(id);
        return modelMapper.map(retVal, RefereeDTO.class);
    }
}
