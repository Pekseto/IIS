package com.iis.repository;

import com.iis.model.Referee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefereeRepository extends JpaRepository<Referee, Long> {

    //Optional<Judge> findBy(long id);
}
