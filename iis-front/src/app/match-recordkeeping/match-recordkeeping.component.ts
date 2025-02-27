import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { RecordKeeperService } from '../services/record-keeper.service';
import { Match, MatchRoster } from '../model/match.model';
import { Player } from '../model/player.model';
import { MatchEvent } from '../model/match-event.model';
import { MatchState } from '../model/match-state.model';
import { PlayerMatchStats } from '../model/player-match-stats.model';

@Component({
  selector: 'app-match-recordkeeping',
  templateUrl: './match-recordkeeping.component.html',
  styleUrls: ['./match-recordkeeping.component.css']
})
export class MatchRecordkeepingComponent implements OnInit {
  match!: Match;
  matchState?: MatchState;
  homeRoster?: MatchRoster;
  awayRoster?: MatchRoster;
  selectedPlayer?: Player;
  events: MatchEvent[] = [];
  activePlayersStats: PlayerMatchStats[] = [];

  constructor(
    private route: ActivatedRoute,
    private service: RecordKeeperService,
    private router: Router,
  ) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const matchId = params['matchId']

      this.service.getMatch(matchId).subscribe({
        next: (response) => {
          this.match = response
          this.homeRoster = this.match.homeRoster
          this.awayRoster = this.match.awayRoster

          this.service.getActivePlayersStats(matchId, this.homeRoster.id!, this.awayRoster.id!).subscribe({
            next: response => {
              this.activePlayersStats = response;
            }
          })
        }
      })
      this.service.getMatchStateByMatchId(matchId).subscribe({
        next: (response) => {
          this.matchState = response
        }
      })
      this.service.getMatchEventsByMatchId(matchId).subscribe({
        next: (response) => {
          this.events = response
        }
      })
    })
  }

  selectPlayer(player: Player) {
    this.selectedPlayer = player;
  }

  unselectPlayer() {
    this.selectedPlayer = undefined;
  }

  updateMatchState(matchState: MatchState) {
    this.matchState! = matchState
  }

  saveMatchState() {
    this.service.updateMatchStateForMatch(this.matchState!).subscribe()
    this.service.updatePlayersTimePlayed(this.activePlayersStats).subscribe()
  }

  timeOut(isHomeTeam: boolean) {
    if (this.matchState?.quarter == 1 || this.matchState?.quarter == 2){
      if(isHomeTeam) this.matchState!.firstHalfTimeoutsHome -= 1
      else this.matchState!.firstHalfTimeoutsAway -= 1
    }
    else{
      if(isHomeTeam) this.matchState!.secondHalfTimeoutsHome-= 1
      else this.matchState!.secondHalfTimeoutsAway -= 1
    }

    const matchEvent: MatchEvent = {
      type: "TIME_OUT",
      minute: this.matchState!.minute,
      second: this.matchState!.second,
      perpetratorName: isHomeTeam ? this.match.homeTeam.name : this.match.awayTeam.name,
      perpetratorId: isHomeTeam ? this.match.homeTeam.id : this.match.awayTeam.id,
      matchId: this.match.id,
      period: this.matchState!.quarter,
    }
    this.service.addEvent(matchEvent).subscribe({
      next: response => {
        this.events?.unshift(response)
      }
    })
    this.service.updateMatchStateForMatch(this.matchState!).subscribe()
    this.service.updatePlayersTimePlayed(this.activePlayersStats).subscribe()
  }

  addEvent(eventType: string) {
    const matchEvent: MatchEvent = {
      type: eventType,
      minute: this.matchState!.minute,
      second: this.matchState!.second,
      perpetratorName: this.selectedPlayer?.name! + ' ' + this.selectedPlayer?.surname,
      perpetratorId: this.selectedPlayer?.id!,
      matchId: this.match.id,
      period: this.matchState!.quarter,
    }

    this.service.addEvent(matchEvent).subscribe({
      next: (response) => {
        this.events?.unshift(response)

        switch (eventType) {
          case "TWO_POINTER":
            if (this.selectedPlayer?.teamId == this.match.homeTeam.id) this.matchState!.homePoints += 2
            else this.matchState!.awayPoints += 2
            break;
          case "THREE_POINTER":
            if (this.selectedPlayer?.teamId == this.match.homeTeam.id) this.matchState!.homePoints += 3
            else this.matchState!.awayPoints += 3
            break;
          case "FREE_THROW_IN":
            if (this.selectedPlayer?.teamId == this.match.homeTeam.id) this.matchState!.homePoints += 1
            else this.matchState!.awayPoints += 1
            break;
          case "UNSPORTSMANLIKE_FOUL":
          case "FOUL":
            switch (this.matchState?.quarter) {
              case 1:
                if (this.selectedPlayer?.teamId == this.match.homeTeam.id) this.matchState!.firstQuarterFoulsHome += 1
                else this.matchState!.firstQuarterFoulsAway += 1
                break;
              case 2:
                if (this.selectedPlayer?.teamId == this.match.homeTeam.id) this.matchState!.secondQuarterFoulsHome += 1
                else this.matchState!.secondQuarterFoulsAway += 1
                break;
              case 3:
                if (this.selectedPlayer?.teamId == this.match.homeTeam.id) this.matchState!.thirdQuarterFoulsHome += 1
                else this.matchState!.thirdQuarterFoulsAway += 1
                break;
              case 4:
                if (this.selectedPlayer?.teamId == this.match.homeTeam.id) this.matchState!.fourthQuarterFoulsHome += 1
                else this.matchState!.fourthQuarterFoulsAway += 1
                break;
            }
            break;
          case "TIME_OUT":
            if (this.selectedPlayer?.teamId == this.match.homeTeam.id) this.matchState!.homePoints += 1
            else this.matchState!.awayPoints += 1
            break;
          default:
        }
        
        this.selectedPlayer = undefined
        this.service.updateMatchStateForMatch(this.matchState!).subscribe()
        this.service.updatePlayersTimePlayed(this.activePlayersStats).subscribe()
      }
    })
  }

  endMatch(){
    //Zavrsiti mec
    this.matchState!.finished = true
    this.service.updateMatchStateForMatch(this.matchState!).subscribe({
      next: response => {
        this.router.navigate([`/match-stats/${this.match.id}`])
      }
    })

    

    //Izracunati naprednu statistiku igraca
    
  }
}
