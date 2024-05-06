package com.iis.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "regular_season_schedules")
public class RegularSeasonSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique = true, nullable = false)
    private Long id;

    @OneToMany(mappedBy = "regularSeasonSchedule", cascade = CascadeType.ALL)
    private List<Match> matches;

    @Column(name="season_start", nullable = true)
    private Date seasonStart;

    @Column(name="season_end", nullable = true)
    private Date seasonEnd;
}
