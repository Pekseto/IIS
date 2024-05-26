package com.iis.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=true)
@Data
@Entity
@Table(name="referees")
public class Referee extends User {

    @Column(name = "point", nullable = false)
    private double point;

    @Column(name = "rank", nullable = true)
    private int rank;
}
