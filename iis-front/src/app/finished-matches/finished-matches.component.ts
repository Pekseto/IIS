import { Component, OnInit } from '@angular/core';
import { Match } from '../model/match.model';
import { MatchService } from '../services/match.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-finished-matches',
  templateUrl: './finished-matches.component.html',
  styleUrls: ['./finished-matches.component.css']
})
export class FinishedMatchesComponent implements OnInit {
  matches: Match[] = [];

  constructor(
    private matchService: MatchService,
    private router: Router,
  ) { }

  ngOnInit(): void {
    this.matchService.getAll().subscribe({
      next: (response) => {
        this.matches = response;
      }
    })
  }
}
