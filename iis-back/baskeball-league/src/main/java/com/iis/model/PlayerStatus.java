package com.iis.model;

import com.fasterxml.jackson.annotation.JsonFormat;

public enum PlayerStatus {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    ACTIVE,
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    INACTIVE,
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    INJURED
}
