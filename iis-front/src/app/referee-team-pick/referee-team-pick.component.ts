import { Component, OnInit } from '@angular/core';
import { Referee } from '../model/referee.model';
import { Match } from '../model/match.model';
import { RefereeService } from '../services/referee.service';
import { MatchService } from '../services/match.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-referee-team-pick',
  templateUrl: './referee-team-pick.component.html',
  styleUrls: ['./referee-team-pick.component.css']
})
export class RefereeTeamPickComponent implements OnInit {
  delegatedReferees: Referee[] = [];
  referees: Referee[] = [];
  recommendedReferees: Referee[] = [];
  referee!: Referee;
  match!: Match;
  matchId!: number;

  constructor (private refereeService: RefereeService,
    private matchService: MatchService,
    private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.matchId = this.route.snapshot.params['id'];
    this.getRecommendation();
    this.getReferees();
    this.getMatch();
  }

  getMatch() {
    this.matchService.getMatch(this.matchId).subscribe({
      next: (response) => {
        this.match = response;
      }
    });
  }

  getRecommendation() {
    this.refereeService.getRecommendation().subscribe({
      next: (response) => {
        this.recommendedReferees = response;
      }
    })
  }

  getReferees() {
    this.refereeService.getAll().subscribe({
      next: (response) => {
        this.referees = response;
      }
    })
  }

  delegate(referee: Referee) {
    let index = 0;
    if(this.delegatedReferees.length == 0)
      {
        this.delegatedReferees[0] = referee;
        return;
      }
    this.delegatedReferees.forEach(element => {
      if(element.id == 0 || element == null){
        this.delegatedReferees[index] = referee;
      }
      index++;
    });
    if(index < 4)
      {
        this.delegatedReferees[index] = referee
      }
  }

  cancel(id: number) {
    let index = 0;
    this.delegatedReferees.forEach(element => {
      if(element.id == id) {
        this.delegatedReferees[index] = this.referee;
      }
      index++
    });
  }

  setRefereeTeam() {
    let obj = {
      matchId: this.match?.id,
      mainRefereeId: this.delegatedReferees[0].id,
      secondRefereeId: this.delegatedReferees[1].id,
      thirdRefereeId: this.delegatedReferees[2].id,
      fourthRefereeId: this.delegatedReferees[3].id,
    };
    this.matchService.setRefereeTeam(obj).subscribe();
  }
}