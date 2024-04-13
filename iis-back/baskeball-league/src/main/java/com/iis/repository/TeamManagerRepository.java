package com.iis.repository;

import com.iis.model.TeamManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamManagerRepository extends JpaRepository<TeamManager,Long> {
}
