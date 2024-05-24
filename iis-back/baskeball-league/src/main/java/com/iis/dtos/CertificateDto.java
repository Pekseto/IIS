package com.iis.dtos;

import com.iis.model.CertificationStatus;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class CertificateDto {
    private long id;
    private String path;
    private Date date;
    private String name;
    private CertificationStatus certificationStatus;
}
