import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Registration } from '../model/registration.model';
import { TeamService } from '../services/team.service';
import { Team } from '../model/team.model';

@Component({
  selector: 'app-team-manager-registration',
  templateUrl: './team-manager-registration.component.html',
  styleUrls: ['./team-manager-registration.component.css']
})
export class TeamManagerRegistrationComponent {
  teams: Team[] = [];
  selectedTeam!: Team;

  constructor(
    private authService: AuthService,
    private teamService: TeamService,
    private router: Router
  ) {}

  registrationForm = new FormGroup({
    name: new FormControl('', [Validators.required, Validators.pattern('^[A-Z][a-z]*$')]),
    surname: new FormControl('', [Validators.required, Validators.pattern('^[A-Z][a-z]*$')]),
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl(''),
    birthday: new FormControl('', [Validators.required]),
    jmbg: new FormControl('', [Validators.required, Validators.pattern('^[0-9]*')]),
    phoneNumber: new FormControl('', [Validators.required, Validators.pattern('[0-9 -]*')]),
    city: new FormControl('', [Validators.required, Validators.pattern('^[A-Z][a-z]*$')]),
    country: new FormControl('', [Validators.required, Validators.pattern('^[A-Z][a-z]*$')]),
  });

  ngOnInit(): void {
    this.teamService.getAll().subscribe(data => {
      this.teams = data;
    })
  }

  register(): void {
    const registration: Registration = {
      name: this.registrationForm.value.name || "",
      surname: this.registrationForm.value.surname || "",
      email: this.registrationForm.value.email || "",
      password: this.registrationForm.value.password || "",
      birthday: this.registrationForm.value.birthday || "",
      jmbg: this.registrationForm.value.jmbg || "",
      phoneNumber: this.registrationForm.value.phoneNumber || "",
      city: this.registrationForm.value.city || "",
      country: this.registrationForm.value.country || "",
      role: "TEAM_MANAGER",
      height: 0,
      weight: 0,
      status: '',
      team: this.selectedTeam ? this.selectedTeam.id : undefined,
    };

    if (this.registrationForm.valid) {
      this.authService.register(registration).subscribe({
        next: (response) => {
          this.router.navigate(['']);
        },
      });
    }
  }

}
