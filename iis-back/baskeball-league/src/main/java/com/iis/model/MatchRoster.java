package com.iis.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper=false)
@Data
@Entity
@Table(name="match_rosters")
public class MatchRoster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique = true, nullable = false)
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "match_roster_bench_players",
            joinColumns = @JoinColumn(name = "match_roster_id"),
            inverseJoinColumns = @JoinColumn(name = "player_id")
    )
    private List<Player> benchPlayers;

    @ManyToMany
    @JoinTable(
            name = "match_roster_active_five",
            joinColumns = @JoinColumn(name = "match_roster_id"),
            inverseJoinColumns = @JoinColumn(name = "player_id")
    )
    private List<Player> activeFive;

    @ManyToMany
    @JoinTable(
            name = "match_roster_starting_five",
            joinColumns = @JoinColumn(name = "match_roster_id"),
            inverseJoinColumns = @JoinColumn(name = "player_id")
    )
    private List<Player> startingFive;

    public void AddToBench(Player player){
        benchPlayers.add(player);
    }

    public void RemoveFromBench(Player player){
        benchPlayers.remove(player);
    }

    public void AddToStartingFive(Player player){
        startingFive.add(player);
    }

    public void RemoveFromStartingFive(Player player){
        startingFive.remove(player);
    }

    public void AddToActiveFive(Player player){
        activeFive.add(player);
    }

    public void RemoveFromActiveFive(Player player){
        activeFive.remove(player);
    }
}
