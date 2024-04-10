import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Team } from '../model/team.model';
import { TeamService } from '../services/team.service';

@Component({
  selector: 'app-team-registration',
  templateUrl: './team-registration.component.html',
  styleUrls: ['./team-registration.component.css']
})
export class TeamRegistrationComponent {
  constructor(
    private teamService: TeamService,
    private router: Router
  ) {}

  registrationForm = new FormGroup({
    name: new FormControl('', [Validators.required]),
    email: new FormControl('', [Validators.required]),
    address: new FormControl('', [Validators.required]),
    phoneNumber: new FormControl('', [Validators.required]),
    city: new FormControl('', [Validators.required]),
    country: new FormControl('', [Validators.required]),
  });

  register(): void {
    const team: Team = {
      name: this.registrationForm.value.name || "",
      email: this.registrationForm.value.email || "",
      address: this.registrationForm.value.address || "",
      phoneNumber: this.registrationForm.value.phoneNumber || "",
      city: this.registrationForm.value.city || "",
      country: this.registrationForm.value.country || "",
    };

    if (this.registrationForm.valid) {
      this.teamService.register(team).subscribe({
        next: (response) => {
          this.router.navigate(['home']);
        },
      });
    }
  }

}
