package com.iis.repository;

import com.iis.model.PlayerMatchStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerMatchStatsRepository extends JpaRepository<PlayerMatchStats, Long> {
    @Query("SELECT pms FROM PlayerMatchStats pms WHERE pms.matchId = ?1 AND pms.teamId = ?2")
    List<PlayerMatchStats> getAllForTeamOnMatch(long matchId, long teamId);
}
