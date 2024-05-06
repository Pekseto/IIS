package com.iis.repository;

import com.iis.model.RegularSeasonSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegularSeasonScheduleRepository extends JpaRepository<RegularSeasonSchedule,Long> {
}
