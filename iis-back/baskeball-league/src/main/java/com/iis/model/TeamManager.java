package com.iis.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name="teams_managers")
public class TeamManager extends User {

    @OneToOne
    @JoinColumn(name = "team_id")
    private Team team;
}
