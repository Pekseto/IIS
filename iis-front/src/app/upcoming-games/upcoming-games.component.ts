import { Component, OnInit } from '@angular/core';
import { Match } from '../model/match.model';
import { MatchService } from '../services/match.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-upcoming-games',
  templateUrl: './upcoming-games.component.html',
  styleUrls: ['./upcoming-games.component.css']
})
export class UpcomingGamesComponent implements OnInit {
  matches: Match[] = [];

  constructor(private matchService: MatchService,
    private router: Router
  ) {}
  
  ngOnInit(): void {
    this.reload();
  }

  reload() {
    this.matchService.getAll().subscribe({
      next: (response) => {
        this.matches = response;
      }
    })
  }

  goToMatchPage(matchId: number) {
    this.router.navigate(['/referee-pick', matchId]);
  }
}