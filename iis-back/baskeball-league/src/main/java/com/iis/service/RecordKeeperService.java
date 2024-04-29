package com.iis.service;

import com.iis.dtos.RecordKeeperDto;

public interface RecordKeeperService {

    RecordKeeperDto Register(RecordKeeperDto recordKeeperDTO);
    RecordKeeperDto Update(RecordKeeperDto recordKeeperDTO);
    RecordKeeperDto GetById(long id);
}
