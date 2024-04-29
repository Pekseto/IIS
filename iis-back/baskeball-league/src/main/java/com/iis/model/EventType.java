package com.iis.model;

import com.fasterxml.jackson.annotation.JsonFormat;

public enum EventType {

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    TWO_POINTER,
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    THREE_POINTER,
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    FREE_THROW_IN,
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    MISSED_TWO_POINTER,
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    MISSED_THREE_POINTER,
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    MISSED_FREE_THROW,
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    DEF_REBOUND,
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    OFF_REBOUND,
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    ASSIST,
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    TURNOVER,
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    STEAL,
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    BLOCK,
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    SHOT_REJECTED,
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    FOUL,
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    FOUL_DRAWN,
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    TECHNICAL_FOUL,
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    UNSPORTSMANLIKE_FOUL,

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    TIME_OUT,
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    OUT,
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    IN,
    ;

}
