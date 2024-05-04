import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Match } from 'src/app/model/match.model';
import { User } from 'src/app/model/user.model';
import { AuthService } from 'src/app/services/auth.service';
import { MatchService } from 'src/app/services/match.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  user?: User;
  matches: Match[] = [];

  constructor(
    private authService: AuthService,
    private matchService: MatchService,
    private router: Router,
  ) { }

  ngOnInit(): void {
    this.authService.user$.subscribe(user => {
      this.user = user
    })
    this.matchService.getAll().subscribe({
      next: (response) => {
        this.matches = response

        //Kenja datum i vreme sa beka pa mora ovako
        this.matches.forEach(match => {
          const matchDay: any = match.matchDay
          match.matchDay = new Date(matchDay[0], matchDay[1], matchDay[2], matchDay[3], matchDay[4])
        });
      }
    })
  }

  beginMatch(match: Match){
    this.router.navigate([`/match-roster-input/${match.id}`])
  }

}
