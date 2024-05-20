package com.iis.service;

import com.iis.dtos.RegularSeasonScheduleDto;

public interface RegularSeasonScheduleService {
    RegularSeasonScheduleDto generateSchedule();

    RegularSeasonScheduleDto save(RegularSeasonScheduleDto schedule);
    boolean checkIfScheduleAlreadyExists(int year);

    RegularSeasonScheduleDto getThisYearSchedule(int year);
}
