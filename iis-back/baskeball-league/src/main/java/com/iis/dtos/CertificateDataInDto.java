package com.iis.dtos;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Date;

@Data
public class CertificateDataInDto {
    private MultipartFile uploadFile;
    private String certificateName;
    private Date certificateDate;
    private Long refereeId;
}
