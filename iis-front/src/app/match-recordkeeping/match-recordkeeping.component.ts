import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { RecordKeeperService } from '../services/record-keeper.service';
import { Match, MatchRoster } from '../model/match.model';
import { Player } from '../model/player.model';
import { MatchEvent } from '../model/match-event.model';
import { MatchState } from '../model/match-state.model';

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

  private quarter: number = 1;
  private minute: number = 10;
  private second: number = 0;

  constructor(
    private route: ActivatedRoute,
    private service: RecordKeeperService,
  ) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const matchId = params['matchId']

      this.service.getMatch(matchId).subscribe({
        next: (response) => {
          this.match = response
          this.homeRoster = this.match.homeRoster
          this.awayRoster = this.match.awayRoster
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

  updateTime(time: number[]) {
    this.minute = time[0]
    this.second = time[1]
  }

  updateQuarter(quarter: number) {
    this.quarter = quarter
  }

  timeOut(isHomeTeam: boolean) {
    if (this.quarter == 1 || this.quarter == 2){
      if(isHomeTeam) this.matchState!.firstHalfTimeoutsHome -= 1
      else this.matchState!.firstHalfTimeoutsAway -= 1
    }
    else{
      if(isHomeTeam) this.matchState!.secondHalfTimeoutsHome-= 1
      else this.matchState!.secondHalfTimeoutsAway -= 1
    }

    const matchEvent: MatchEvent = {
      type: "TIME_OUT",
      minute: this.minute,
      second: this.second,
      perpetratorName: isHomeTeam ? this.match.homeTeam.name : this.match.awayTeam.name,
      perpetratorId: isHomeTeam ? this.match.homeTeam.id : this.match.awayTeam.id,
      matchId: this.match.id,
      period: this.quarter,
    }
    this.service.addEvent(matchEvent).subscribe({
      next: response => {
        this.events?.unshift(response)
      }
    })
    this.service.updateMatchStateForMatch(this.matchState!).subscribe()
  }

  addEvent(eventType: string) {
    const matchEvent: MatchEvent = {
      type: eventType,
      minute: this.minute,
      second: this.second,
      perpetratorName: this.selectedPlayer?.name! + ' ' + this.selectedPlayer?.surname,
      perpetratorId: this.selectedPlayer?.id!,
      matchId: this.match.id,
      period: this.quarter,
    }

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
        switch (this.quarter) {
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

    this.service.addEvent(matchEvent).subscribe({
      next: (response) => {
        this.events?.unshift(response)
        this.selectedPlayer = undefined

        this.service.updateMatchStateForMatch(this.matchState!).subscribe()
      }
    })
  }
}
