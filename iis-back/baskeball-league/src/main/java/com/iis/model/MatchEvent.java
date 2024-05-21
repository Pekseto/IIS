package com.iis.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalTime;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "match_events")
public class MatchEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique = true, nullable = false)
    private Long id;

    @Getter
    @Enumerated(EnumType.STRING)
    @Column(name="event_type", nullable = false)
    private EventType type;

    @Column(name="minute", nullable = false)
    private int minute;

    @Column(name="second", nullable = false)
    private int second;

    @Column(name="perpetrator_name", nullable = false)
    private String perpetratorName;

    @Column(name="perpetrator_id", nullable = false)
    private Long perpetratorId;

    @Column(name="match_id", nullable = false)
    private Long matchId;

    @Column(name="period", nullable = false)
    private int period;
}
