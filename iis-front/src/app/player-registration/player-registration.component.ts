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
    birthday: new FormControl('', [Validators.required]), // Prilagođena validacija za datum
    phoneNumber: new FormControl('', [Validators.required, Validators.pattern('[0-9 -]*')]), // Primer za broj telefona
    city: new FormControl('', [Validators.required, Validators.pattern('^[A-Z][a-z]*$')]),
    country: new FormControl('', [Validators.required, Validators.pattern('^[A-Z][a-z]*$')]),
    jmbg: new FormControl('', [Validators.required, Validators.pattern('^[0-9]*')]),
    height: new FormControl(0, [Validators.required, Validators.min(0)]), // Minimalna vrednost visine
    weight: new FormControl(0, [Validators.required, Validators.min(0)]), // Minimalna vrednost težine
    status: new FormControl('', [Validators.required]),
    jerseyNumber: new FormControl(0, [Validators.required, Validators.min(0)]),
    team: new FormControl(undefined, [Validators.required])
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
      team: this.registrationForm.value.team ? this.registrationForm.value.team : undefined,
      jmbg: this.registrationForm.value.jmbg || "",
      jerseyNumber: this.registrationForm.value.jerseyNumber || 0
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
