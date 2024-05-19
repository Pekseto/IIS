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
        //Kenja datum i vreme sa beka pa mora ovako
        response.forEach(element => {

          const match = element
          match.matchDay = new Date(match.matchDay[0], match.matchDay[1] - 1, match.matchDay[2], match.matchDay[3], match.matchDay[4])
          this.matches.push(match)

        });
      }
    })
  }

  beginMatch(match: Match){
    this.router.navigate([`/match-roster-input/${match.id}`])
  }

}
