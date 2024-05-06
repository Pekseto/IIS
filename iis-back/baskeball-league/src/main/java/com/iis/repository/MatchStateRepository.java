package com.iis.repository;

import com.iis.model.MatchState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchStateRepository extends JpaRepository<MatchState, Long> {
    @Query("SELECT ms FROM MatchState ms WHERE ms.matchId = ?1")
    MatchState getByMatchId(long matchId);
}
