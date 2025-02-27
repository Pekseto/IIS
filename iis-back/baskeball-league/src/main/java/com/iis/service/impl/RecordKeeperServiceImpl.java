package com.iis.service.impl;

import com.iis.dtos.RecordKeeperDto;
import com.iis.model.RecordKeeper;
import com.iis.model.Role;
import com.iis.repository.RecordKeeperRepository;
import com.iis.service.RecordKeeperService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecordKeeperServiceImpl implements RecordKeeperService {
    private final RecordKeeperRepository repo;
    ModelMapper modelMapper = new ModelMapper();

    @Override
    public RecordKeeperDto Register(RecordKeeperDto recordKeeperDTO) {
        recordKeeperDTO.setPassword("recordkeeper");
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        recordKeeperDTO.setPassword(encoder.encode(recordKeeperDTO.getPassword()));

        var recordKeeper = modelMapper.map(recordKeeperDTO, RecordKeeper.class);
        recordKeeper.setRole(Role.RECORD_KEEPER);
        
        var retVal = repo.save(recordKeeper);
        return modelMapper.map(retVal, RecordKeeperDto.class);
    }

    @Override
    public RecordKeeperDto Update(RecordKeeperDto recordKeeperDTO) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        recordKeeperDTO.setPassword(encoder.encode(recordKeeperDTO.getPassword()));

        var updatedRecordKeeper = modelMapper.map(recordKeeperDTO, RecordKeeper.class);
        var recordKeeper = repo.getReferenceById(recordKeeperDTO.getId());
        updatedRecordKeeper.setTokens(recordKeeper.getTokens());

        var retVal = repo.save(updatedRecordKeeper);
        return modelMapper.map(retVal, RecordKeeperDto.class);
    }

    @Override
    public RecordKeeperDto GetById(long id) {
        var retVal = repo.findById(id);
        return modelMapper.map(retVal, RecordKeeperDto.class);
    }

    @Override
    public List<RecordKeeperDto> GetAll() {
        var recordKeepers = repo.findAll();
        return recordKeepers.stream()
                .map(recordKeeper -> modelMapper.map(recordKeeper, RecordKeeperDto.class))
                .collect(Collectors.toList());
    }
}
