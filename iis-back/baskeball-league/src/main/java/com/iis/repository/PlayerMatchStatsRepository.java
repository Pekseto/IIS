package com.iis.repository;

import com.iis.model.PlayerMatchStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PlayerMatchStatsRepository extends JpaRepository<PlayerMatchStats, Long> {
    @Query("SELECT pms FROM PlayerMatchStats pms WHERE pms.matchId = ?1 AND pms.teamId = ?2")
    List<PlayerMatchStats> getAllForTeamOnMatch(long matchId, long teamId);

    @Query("SELECT pms FROM PlayerMatchStats pms WHERE pms.matchId = ?1")
    List<PlayerMatchStats> getAllForMatch(long matchId);

    @Modifying
    @Transactional
    @Query("UPDATE PlayerMatchStats pms SET pms.MinutesPlayed = ?1, pms.SecondsPlayed = ?2 WHERE pms.id = ?3")
    void updateTimePlayed(int minutesPlayed, int secondsPlayed, long id);
}
