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

    @Column(name="time", nullable = false)
    private LocalTime time;

    @Column(name="playerId", nullable = false)
    private Long playerId;

    @Column(name="matchId", nullable = false)
    private Long matchId;

    @Column(name="period", nullable = false)
    private int period;
}
