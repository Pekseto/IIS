package com.iis.repository;

import com.iis.dtos.RegularSeasonScheduleDto;
import com.iis.model.RegularSeasonSchedule;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

@Repository
public interface RegularSeasonScheduleRepository extends JpaRepository<RegularSeasonSchedule,Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name = "jakarta.persistence.lock.timeout", value ="1000")})
    @Query("SELECT \n" +
            "    CASE \n" +
            "        WHEN COUNT(*) > 0 THEN TRUE \n" +
            "        ELSE FALSE \n" +
            "    END AS schedule_exists\n" +
            "FROM \n" +
            "    RegularSeasonSchedule \n" +
            "WHERE \n" +
            "    EXTRACT(YEAR FROM seasonStart) = :year")
    boolean checkIfScheduleAlreadyExists(int year);

    @Query("SELECT r " +
            "FROM RegularSeasonSchedule r " +
            "WHERE EXTRACT(YEAR FROM r.seasonStart) = :year")
    RegularSeasonSchedule getThisYearSchedule(int year);
}
