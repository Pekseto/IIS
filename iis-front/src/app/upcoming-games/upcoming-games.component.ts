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
        response.forEach(element => {
          const match = element
          match.matchDay = new Date(match.matchDay[0], match.matchDay[1] - 1, match.matchDay[2], match.matchDay[3], match.matchDay[4])
          this.matches.push(match)
        });
      }
    })
  }

  goToMatchPage(matchId: number) {
    this.router.navigate(['/referee-pick', matchId]);
  }
}
