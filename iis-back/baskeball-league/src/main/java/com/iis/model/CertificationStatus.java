package com.iis.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CertificationStatus {

    REQUESTED("Requested"),
    ACCEPTED("Accepted"),
    DECLINED("Declined");

    private final String certificationStatus;
}
