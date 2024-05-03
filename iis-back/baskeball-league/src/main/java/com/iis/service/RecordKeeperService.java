package com.iis.service;

import com.iis.dtos.RecordKeeperDto;

import java.util.List;

public interface RecordKeeperService {

    RecordKeeperDto Register(RecordKeeperDto recordKeeperDTO);
    RecordKeeperDto Update(RecordKeeperDto recordKeeperDTO);
    RecordKeeperDto GetById(long id);
    List<RecordKeeperDto> GetAll();
}
