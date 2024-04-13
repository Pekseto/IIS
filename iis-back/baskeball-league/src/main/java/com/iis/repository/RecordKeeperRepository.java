package com.iis.repository;

import com.iis.model.RecordKeeper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordKeeperRepository extends JpaRepository<RecordKeeper, Long> {

}
