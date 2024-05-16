package com.iis.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EventType {

    TWO_POINTER("Two Pointer"),
    THREE_POINTER("Three Pointer"),
    FREE_THROW_IN("Free Throw In"),
    MISSED_TWO_POINTER("Missed Two Pointer"),
    MISSED_THREE_POINTER("Missed Three Pointer"),
    MISSED_FREE_THROW("Missed Free Throw"),
    DEF_REBOUND("Defensive Rebound"),
    OFF_REBOUND("Offensive Rebound"),
    ASSIST("Assist"),
    TURNOVER("Turnover"),
    STEAL("Steal"),
    BLOCK("Block"),
    SHOT_REJECTED("Shot Rejected"),
    FOUL("Foul"),
    FOUL_DRAWN("Foul Drawn"),
    TECHNICAL_FOUL("Technical Foul"),
    UNSPORTSMANLIKE_FOUL("Unsportsmanlike Foul"),

    TIME_OUT("Time Out"),
    OUT("Out"),
    IN("In");

    private final String eventName;

}
