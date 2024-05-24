package com.iis.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=false)
@Data
@Entity
@Table(name="referee_match_stats")
public class RefereeMatchStats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name= "referee_id", nullable = false)
    private Referee referee;

    @ManyToOne
    @JoinColumn(name= "match_id", nullable = false)
    private Match match;

    @Column(name = "points", nullable = false)
    private int points;
}
