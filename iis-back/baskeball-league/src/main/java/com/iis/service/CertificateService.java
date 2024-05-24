package com.iis.service;

import com.iis.dtos.CertificateDto;
import com.iis.dtos.CertificationStatusDataIn;

import java.util.List;

public interface CertificateService {
    List<CertificateDto> GetAllCertificates(Long userId);
    List<CertificateDto> GetAllRequests();
    String ChangeStatus(CertificationStatusDataIn dataIn);
}
