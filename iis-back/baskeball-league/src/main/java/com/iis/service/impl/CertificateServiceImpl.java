package com.iis.service.impl;

import com.iis.dtos.CertificateDto;
import com.iis.dtos.CertificationStatusDataIn;
import com.iis.dtos.MatchDto;
import com.iis.model.CertificationStatus;
import com.iis.repository.CertificateRepository;
import com.iis.service.CertificateService;
import com.iis.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CertificateServiceImpl implements CertificateService {

    private final CertificateRepository certificateRepository;
    private final Mapper mapper;

    @Override
    public List<CertificateDto> GetAllCertificates(Long userId) {
        var valFromDb = certificateRepository.findAllByRefereeId(userId);
        return valFromDb.stream()
                .map(certificate -> mapper.map(certificate, CertificateDto.class))
                .toList();
    }

    @Override
    public List<CertificateDto> GetAllRequests() {
        var valFromDb = certificateRepository.findByCertificationStatus(CertificationStatus.REQUESTED);
        return valFromDb.stream()
                .map(certificate -> mapper.map(certificate, CertificateDto.class))
                .toList();
    }

    @Override
    public String ChangeStatus(CertificationStatusDataIn dataIn) {
        var certificateFromDb = certificateRepository.getById(dataIn.getCertificationId());
        if(dataIn.getCertificationStatus().toLowerCase().equals("accept")) {
            certificateFromDb.setCertificationStatus(CertificationStatus.ACCEPTED);
            //certificateFromDb.setPoints(3);
        }
        else {
            certificateFromDb.setCertificationStatus(CertificationStatus.DECLINED);
        }
        certificateRepository.save(certificateFromDb);
        return "Sucessfully accepted certification";
    }
}
