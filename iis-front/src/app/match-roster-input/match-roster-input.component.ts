import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AdministrationService } from '../services/administration.service';
import { RecordKeeperService } from '../services/record-keeper.service';
import { Match, MatchRoster } from '../model/match.model';
import { Player } from '../model/player.model';
import { MatchState } from '../model/match-state.model';

@Component({
  selector: 'app-match-roster-input',
  templateUrl: './match-roster-input.component.html',
  styleUrls: ['./match-roster-input.component.css']
})
export class MatchRosterInputComponent implements OnInit {
  match!: Match;
  homePlayers: Player[] = [];
  awayPlayers: Player[] = [];
  homeAddedPlayers: Player[] = [];
  awayAddedPlayers: Player[] = [];
  homeRoster?: MatchRoster;
  awayRoster?: MatchRoster;

  constructor(
    private route: ActivatedRoute,
    private service: RecordKeeperService,
    private router: Router,
  ) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const matchId = params['matchId']

      this.service.getMatch(matchId).subscribe({
        next: (response: Match) => {
          if (response.homeRoster != null) this.router.navigate([`/match-recordkeeping/${matchId}`])
          this.match = response
          this.homePlayers = this.match?.homeTeam.players!
          this.awayPlayers = this.match?.awayTeam.players!
        }
      })
    })

    this.homeRoster = {
      id: 0,
      benchPlayers: [],
      startingFive: [],
      activeFive: [],
    }
    this.awayRoster = {
      id: 0,
      benchPlayers: [],
      startingFive: [],
      activeFive: [],
    }
  }

  addToStartingFive(player: Player) {
    if (player.teamId == this.match?.homeTeam.id) {
      this.homeRoster?.startingFive.push(player)
      this.homeRoster?.activeFive.push(player)
      this.homeRoster!.benchPlayers = this.homeRoster!.benchPlayers.filter(p => p.id != player.id)
    } else {
      this.awayRoster?.startingFive.push(player)
      this.awayRoster?.activeFive.push(player)
      this.awayRoster!.benchPlayers = this.awayRoster!.benchPlayers.filter(p => p.id != player.id)
    }
  }

  removeFromStartingFive(player: Player) {
    if (player.teamId == this.match?.homeTeam.id) {
      this.homeRoster!.startingFive = this.homeRoster!.startingFive.filter(p => p.id != player.id)
      this.homeRoster!.activeFive = this.homeRoster!.activeFive.filter(p => p.id != player.id)
      this.homeRoster?.benchPlayers.push(player)
    } else {
      this.awayRoster!.startingFive = this.awayRoster!.startingFive.filter(p => p.id != player.id)
      this.awayRoster!.activeFive = this.awayRoster!.activeFive.filter(p => p.id != player.id)
      this.awayRoster?.benchPlayers.push(player)
    }
  }

  addToBench(player: Player) {
    if (player.teamId == this.match?.homeTeam.id) {
      this.homeRoster?.benchPlayers.push(player)
      this.homeAddedPlayers.push(player)
      this.homePlayers = this.homePlayers.filter(p => p.id != player.id)
    }
    else {
      this.awayRoster?.benchPlayers.push(player)
      this.awayAddedPlayers.push(player)
      this.awayPlayers = this.awayPlayers.filter(p => p.id != player.id)
    }
  }

  removeFromBench(player: Player) {
    if (player.teamId == this.match?.homeTeam.id) {
      this.homePlayers.push(player)
      this.homeAddedPlayers = this.homeAddedPlayers.filter(p => p.id != player.id)
      this.homeRoster!.benchPlayers = this.homeRoster!.benchPlayers.filter(p => p.id != player.id)
      this.homeRoster!.startingFive = this.homeRoster!.startingFive.filter(p => p.id != player.id)
      this.homeRoster!.activeFive = this.homeRoster!.activeFive.filter(p => p.id != player.id)
    }
    else {
      this.awayPlayers.push(player)
      this.awayAddedPlayers = this.awayAddedPlayers.filter(p => p.id != player.id)
      this.awayRoster!.benchPlayers = this.awayRoster!.benchPlayers.filter(p => p.id != player.id)
      this.awayRoster!.startingFive = this.awayRoster!.startingFive.filter(p => p.id != player.id)
      this.awayRoster!.activeFive = this.awayRoster!.activeFive.filter(p => p.id != player.id)
    }
  }

  startMatch() {
    if (this.homeRoster?.startingFive.length == 5 && this.awayRoster?.startingFive.length == 5) {

      this.match!.homeRoster = this.homeRoster!
      this.match!.awayRoster = this.awayRoster!

      const matchState = this.createStartingMatchState();

      console.log(this.match)

      this.service.createMatchRostersForMatch(this.match).subscribe({
        next: (response) => {
          this.service.createMatchStateForMatch(matchState).subscribe({
            next: (response) => {
              this.router.navigate([`/match-recordkeeping/${this.match.id}`])
            }
          })
        }
      })
    }
  }

  createStartingMatchState(): MatchState{
    const matchState: MatchState = {
      matchId: this.match.id,
      homePoints: 0,
      awayPoints: 0,
      firstHalfTimeoutsHome: 2,
      secondHalfTimeoutsHome: 3,
      firstHalfTimeoutsAway: 2,
      secondHalfTimeoutsAway: 3,
      firstQuarterFoulsHome: 0,
      secondQuarterFoulsHome: 0,
      thirdQuarterFoulsHome: 0,
      fourthQuarterFoulsHome: 0,
      firstQuarterFoulsAway: 0,
      secondQuarterFoulsAway: 0,
      thirdQuarterFoulsAway: 0,
      fourthQuarterFoulsAway: 0,
      minute: 10,
      second: 0,
      quarter: 1,
    }
    return matchState;
  }

}
