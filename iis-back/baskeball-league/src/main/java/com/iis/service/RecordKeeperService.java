package com.iis.service;

import com.iis.dto.recordkeeperDTOs.RecordKeeperDTO;

public interface RecordKeeperService {

    RecordKeeperDTO Register(RecordKeeperDTO recordKeeperDTO);
    RecordKeeperDTO Update(RecordKeeperDTO recordKeeperDTO);
    RecordKeeperDTO GetById(long id);
}
