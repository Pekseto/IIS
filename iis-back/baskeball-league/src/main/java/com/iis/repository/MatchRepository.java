package com.iis.repository;

import com.iis.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

    long countByMainRefereeIdOrSecondRefereeIdOrThirdRefereeIdOrFourthRefereeIdAndMatchDayBetween(Long mainRefereeId, Long secondRefereeId, Long thirdRefereeId, Long fourthRefereeId, LocalDate startDate, LocalDate endDate);

    List<Match> findByMainRefereeIdOrSecondRefereeIdOrThirdRefereeIdOrFourthRefereeIdAndMatchDayBetween(Long mainRefereeId, Long secondRefereeId, Long thirdRefereeId, Long fourthRefereeId, LocalDate startDate, LocalDate endDate);
}