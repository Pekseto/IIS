package com.iis.repository;

import com.iis.model.MatchEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchEventRepository extends JpaRepository<MatchEvent, Long> {
}
