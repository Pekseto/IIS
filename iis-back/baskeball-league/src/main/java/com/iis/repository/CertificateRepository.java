package com.iis.repository;

import com.iis.model.Certificate;
import com.iis.model.CertificationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Long> {

    List<Certificate> findAllByRefereeId(Long refereeId);
    List<Certificate> findByCertificationStatus(CertificationStatus certificationStatus);
}
