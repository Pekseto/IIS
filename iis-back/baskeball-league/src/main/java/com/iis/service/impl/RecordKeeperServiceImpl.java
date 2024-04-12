package com.iis.service.impl;

import com.iis.dto.recordkeeperDTOs.RecordKeeperDTO;
import com.iis.model.RecordKeeper;
import com.iis.repository.RecordKeeperRepository;
import com.iis.service.RecordKeeperService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecordKeeperServiceImpl implements RecordKeeperService {
    private final RecordKeeperRepository repo;
    ModelMapper modelMapper = new ModelMapper();

    @Override
    public RecordKeeperDTO Register(RecordKeeperDTO recordKeeperDTO) {
        var retVal = repo.save(modelMapper.map(recordKeeperDTO, RecordKeeper.class));
        return modelMapper.map(retVal, RecordKeeperDTO.class);
    }

    @Override
    public RecordKeeperDTO Update(RecordKeeperDTO recordKeeperDTO) {
        var retVal = repo.save(modelMapper.map(recordKeeperDTO, RecordKeeper.class));
        return modelMapper.map(retVal, RecordKeeperDTO.class);
    }

    @Override
    public RecordKeeperDTO GetById(long id) {
        var retVal = repo.findById(id);
        return modelMapper.map(retVal, RecordKeeperDTO.class);
    }
}
