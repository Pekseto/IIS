package com.iis.service;

import com.iis.dto.judgeDTOs.RefereeDTO;
import com.iis.dtos.CertificateDataInDto;
import com.iis.helpers.SearchIn;
import com.iis.model.Referee;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RefereeService {

    RefereeDTO Register(RefereeDTO judgeDTO);
    RefereeDTO Update(RefereeDTO judgeDTO);
    RefereeDTO GetById(long id);
    Page<Referee> GetAll(SearchIn dataIn);
    List<RefereeDTO> GetAll();
    List<RefereeDTO> GetRecommendation();
    String CreateCertificationRequest(CertificateDataInDto dataIn) throws Exception;
}