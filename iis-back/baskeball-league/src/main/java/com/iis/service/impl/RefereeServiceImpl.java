package com.iis.service.impl;

import com.iis.dto.judgeDTOs.RefereeDTO;
import com.iis.helpers.SearchIn;
import com.iis.model.Referee;
import com.iis.model.Role;
import com.iis.repository.RefereeRepository;
import com.iis.service.RefereeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RefereeServiceImpl implements RefereeService {

    private final RefereeRepository refereeRepository;
    ModelMapper modelMapper = new ModelMapper();

    @Override
    public RefereeDTO Register(RefereeDTO judgeDTO) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        var model = modelMapper.map(judgeDTO, Referee.class);
        model.setRole(Role.REFEREE);
        model.setPassword(encoder.encode(judgeDTO.getPassword()));
        var retVal = refereeRepository.save(model);
        return modelMapper.map(retVal, RefereeDTO.class);
    }

    @Override
    public RefereeDTO Update(RefereeDTO judgeDTO) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        var refereeFromDb = refereeRepository.getById(judgeDTO.getId());

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

        var retVal = refereeRepository.save(refereeFromDb);
        return modelMapper.map(retVal, RefereeDTO.class);
    }

    @Override
    public RefereeDTO GetById(long id) {
        var retVal = refereeRepository.getById(id);
        return modelMapper.map(retVal, RefereeDTO.class);
    }

    @Override
    public Page<Referee> GetAll(SearchIn dataIn) {
        PageRequest page = PageRequest.of(dataIn.getPage(), dataIn.getSize());

        if(dataIn.getSearch() != null && !dataIn.getSearch().isEmpty()) {
            return null;
        }
        else {
            return refereeRepository.findAll(page);
            //return modelMapper.map(retVal, RefereeDTO.class);
        }
    }

    @Override
    public List<RefereeDTO> GetAll() {
        var refereesFromDb = refereeRepository.findAll();
        return refereesFromDb.stream().map(referee -> modelMapper.map(referee, RefereeDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<RefereeDTO> GetRecommendation() {
        PageRequest page = PageRequest.of(1, 3, Sort.Direction.DESC, "point");
        var refereesFromDb = refereeRepository.findAll(page);
        var retVal = refereesFromDb.getContent();
        return retVal.stream().map(referee -> modelMapper.map(referee, RefereeDTO.class)).collect(Collectors.toList());
    }
}
