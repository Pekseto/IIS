import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Registration } from '../model/registration.model';
import { TeamService } from '../services/team.service';
import { Team } from '../model/team.model';

@Component({
  selector: 'app-player-registration',
  templateUrl: './player-registration.component.html',
  styleUrls: ['./player-registration.component.css']
})
export class PlayerRegistrationComponent {

  teams: Team[] = [];
  selectedTeam: any;

  constructor(
    private authService: AuthService,
    private teamService: TeamService,
    private router: Router
  ) {}

  registrationForm = new FormGroup({
    name: new FormControl('', [Validators.required]),
    surname: new FormControl('', [Validators.required]),
    email: new FormControl('', [Validators.required]),
    password: new FormControl(''),
    birthday: new FormControl('', [Validators.required]),
    phoneNumber: new FormControl('', [Validators.required]),
    city: new FormControl('', [Validators.required]),
    country: new FormControl('', [Validators.required]),
    height: new FormControl(-1, [Validators.required]),
    weight: new FormControl(-1, [Validators.required]),
    status: new FormControl('', [Validators.required]),
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
      phoneNumber: this.registrationForm.value.phoneNumber || "",
      city: this.registrationForm.value.city || "",
      country: this.registrationForm.value.country || "",
      role: "PLAYER",
      height: this.registrationForm.value.height || -1,
      weight: this.registrationForm.value.weight || -1,
      status: this.registrationForm.value.status || "",
      team: this.selectedTeam,
    };

    if (this.registrationForm.valid) {
      this.authService.register(registration).subscribe({
        next: (response) => {
          this.router.navigate(['home']);
        },
      });
    }
  }
}
