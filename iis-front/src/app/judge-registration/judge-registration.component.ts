import { Component } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Registration } from '../model/registration.model';
import { Referee } from '../model/referee.model';
import { RefereeService } from '../services/referee.service';

@Component({
  selector: 'app-judge-registration',
  templateUrl: './judge-registration.component.html',
  styleUrls: ['./judge-registration.component.css']
})
export class JudgeRegistrationComponent {
  constructor(
    private authService: AuthService,
    private refereeService: RefereeService,
    private router: Router
  ) { }

  registrationForm = new FormGroup({
    name: new FormControl('', [Validators.required]),
    surname: new FormControl('', [Validators.required]),
    birthday: new FormControl('', [Validators.required]),
    jmbg: new FormControl('', [Validators.required]),
    // address: new FormControl('', [Validators.required]),
    city: new FormControl('', [Validators.required]),
    country: new FormControl('', [Validators.required]),
    phoneNumber: new FormControl('', [Validators.required]),
    email: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required]),
  });

  register(): void {
    const registration: Referee = {
      id: 0,
      name: this.registrationForm.value.name || "",
      surname: this.registrationForm.value.surname || "",
      birthday: this.registrationForm.value.birthday || "",
      jmbg: this.registrationForm.value.jmbg || "",
      // address: this.registrationForm.value.address || "",
      city: this.registrationForm.value.city || "",
      country: this.registrationForm.value.country || "",
      phoneNumber: this.registrationForm.value.phoneNumber || "",
      email: this.registrationForm.value.email || "",
      password: this.registrationForm.value.password || "",
      role: '',
    };
    // const registration: Registration = {
    //   name: this.registrationForm.value.name || "",
    //   surname: this.registrationForm.value.surname || "",
    //   email: this.registrationForm.value.email || "",
    //   password: this.registrationForm.value.password || "",
    //   birthday: this.registrationForm.value.birthday || "",
    //   jmbg: this.registrationForm.value.jmbg || "",
    //   phoneNumber: this.registrationForm.value.phoneNumber || "",
    //   city: this.registrationForm.value.city || "",
    //   country: this.registrationForm.value.country || "",
    //   team: undefined,
    //   role: "REFEREE",
    //   height: 0,
    //   weight: 0,
    //   status: '',
    //   jerseyNumber: 0
    // };

    if (this.registrationForm.valid) {
      this.authService.registerReferee(registration).subscribe({
        next: (response) => {
          this.router.navigate(['home']);
        },
      });
    }
  }
}
