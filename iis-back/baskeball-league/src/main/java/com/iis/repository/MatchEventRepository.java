package com.iis.repository;

import com.iis.model.MatchEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MatchEventRepository extends JpaRepository<MatchEvent, Long> {
    @Query("SELECT me FROM MatchEvent me WHERE me.matchId = ?1 ORDER BY me.period DESC, me.minute ASC, me.second ASC")
    List<MatchEvent> getAllByMatchId(long matchId);
}
