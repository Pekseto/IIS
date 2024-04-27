package com.iis.repository;

import com.iis.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface MatchRepository extends JpaRepository<Match, Long> {

    long countByMainRefereeIdOrSecondRefereeIdOrThirdRefereeIdOrFourthRefereeIdAndMatchDayBetween(long refereeId, LocalDateTime startDate, LocalDateTime endDate);

}