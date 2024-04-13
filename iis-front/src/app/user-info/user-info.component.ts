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
import { CoachService } from '../services/coach.service';

@Component({
  selector: 'app-user-info',
  templateUrl: './user-info.component.html',
  styleUrls: ['./user-info.component.css']
})
export class UserInfoComponent {

  teams: Team[] = [];
  user!: User;
  registeredUser!: Registration;
  usersTeamId: number = -1;
  showPassword: boolean = false;
  password: string | undefined;
  confirmPassword: string | undefined
  isEditing = false;

  constructor(
    private playerService: PlayerService,
    private teamManagerService: TeamManagerService,
    private administrationService: AdministrationService,
    private router: Router,
    private authService: AuthService,
    private teamService: TeamService,
    private coachService: CoachService
  ) {}

  ngOnInit(): void {
    // Prvo dobijamo korisnika
    this.authService.getUser().subscribe(user => {
      this.user = user;
      // Nakon što dobijemo korisnika, u zavisnosti od njegove uloge, pozivamo odgovarajući servis
      if (this.user) {
        if (this.user.role === 'TEAM_MANAGER') {
          this.teamManagerService.getManager(this.user.id).subscribe((data) => {
            this.registeredUser = data;
          });
        } else if (this.user.role === 'PLAYER') {
          this.playerService.getPlayer(this.user.id).subscribe((data) => {
            this.registeredUser = data;
          });
        } else if (this.user.role === 'LEAGUE_ADMIN') {
          this.administrationService.getAdmin(this.user.id).subscribe((data) => {
            this.registeredUser = data;
          });
        }
        else if (this.user.role === 'COACH') {
          this.coachService.getCoach(this.user.id).subscribe((data) => {
            this.registeredUser = data;
          });
        }
      }
    });

    // Nakon što dobijemo korisnika, možemo dohvatiti timove
    this.teamService.getAll().subscribe(data => {
      this.teams = data;
    });
  }

  startEditing() {
    this.isEditing = true; //izbaci
  }

  save(): void{

    this.registeredUser.team = this.usersTeamId;

    if (this.confirmPassword !== undefined && this.password !== undefined) {
      if (this.password === this.confirmPassword) {
          this.registeredUser.password = this.confirmPassword;
      } else {
          alert("Passwords do not match!");
          return;
      }
    }

    if (this.user.role === 'TEAM_MANAGER') {
      this.teamManagerService.save(this.registeredUser).subscribe({
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
    else if (this.user.role === 'COACH') {
      this.coachService.save(this.registeredUser).subscribe({
        next: (response) => {
          this.router.navigate(['']);
        },
      });
    }
  }

  toggleShowPassword(): void {
    this.showPassword = !this.showPassword;
}
}
