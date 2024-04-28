import { Component, OnInit } from '@angular/core';
import { Match } from '../model/match.model';

@Component({
  selector: 'app-upcoming-games',
  templateUrl: './upcoming-games.component.html',
  styleUrls: ['./upcoming-games.component.css']
})
export class UpcomingGamesComponent implements OnInit {
  matches: Match[] = [];
  
  ngOnInit(): void {
  }

}
