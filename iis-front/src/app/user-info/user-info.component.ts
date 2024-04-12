import { Component } from '@angular/core';
import { PlayerService } from '../services/player.service';
import { AdministrationService } from '../services/administration.service';
import { TeamManagerService } from '../services/team-manager.service';
import { Router } from '@angular/router';
import { User } from 'src/app/model/user.model';
import { AuthService } from 'src/app/services/auth.service';
import { Registration } from '../model/registration.model';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Team } from '../model/team.model';
import { TeamService } from '../services/team.service';

@Component({
  selector: 'app-user-info',
  templateUrl: './user-info.component.html',
  styleUrls: ['./user-info.component.css']
})
export class UserInfoComponent {

  teams: Team[] = [];
  user!: User;
  registeredUser!: Registration;
  usersTeam!: Team;

  constructor(
    private playerService: PlayerService,
    private teamManagerService: TeamManagerService,
    private administrationService: AdministrationService,
    private router: Router,
    private authService: AuthService,
    private teamService: TeamService
  ) {}

  ngOnInit(): void {
    this.user = this.authService.user$.getValue();

    this.teamService.getAll().subscribe(data => {
      this.teams = data;
    })

    if (this.user.role === 'TEAM_MANAGER') {
      this.teamManagerService.getManager(this.user.id).subscribe((data) => {
        this.registeredUser = data;
      });
    } else if (this.user.role === 'PLAYER') {
      this.playerService.getPlayer(this.user.id).subscribe((data) => {
        this.registeredUser = data;
      });
    }
    else if (this.user.role === 'LEAGUE_ADMIN') {
      this.administrationService.getAdmin(this.user.id).subscribe((data) => {
        this.registeredUser = data;
      });
    }
  }

  save(): void{
    if (this.user.role === 'TEAM_MANAGER') {
      this.administrationService.save(this.registeredUser).subscribe({
        next: (response) => {
          this.router.navigate(['']);
        },
      });
    } else if (this.user.role === 'PLAYER') {
      this.playerService.save(this.registeredUser).subscribe({
        next: (response) => {
          this.router.navigate(['']);
        },
      });
    }
    else if (this.user.role === 'LEAGUE_ADMIN') {
      this.administrationService.save(this.registeredUser).subscribe({
        next: (response) => {
          this.router.navigate(['']);
        },
      });
    }
  }
}
