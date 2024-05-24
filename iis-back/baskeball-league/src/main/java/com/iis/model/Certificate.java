package com.iis.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Date;

@EqualsAndHashCode(callSuper=false)
@Data
@Entity
@Table(name="certificates")
public class Certificate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique = true, nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "referee_id", nullable = true)
    private Referee referee;

    @Column(name = "path", nullable = false)
    private String path;

    @Column(name = "points", nullable = true)
    private int points;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "date", nullable = false)
    private Date date;

    @Getter
    @Enumerated(EnumType.STRING)
    @Column(name = "certification_status", nullable = false)
    private CertificationStatus certificationStatus;
}
