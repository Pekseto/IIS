import { Component } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Registration } from '../model/registration.model';
import { RecordKeeper } from '../model/record-keeper.model';

@Component({
  selector: 'app-recordkeeper-registration',
  templateUrl: './recordkeeper-registration.component.html',
  styleUrls: ['./recordkeeper-registration.component.css']
})
export class RecordkeeperRegistrationComponent {
  constructor(
    private authService: AuthService,
    private router: Router
  ) { }

  registrationForm = new FormGroup({
    name: new FormControl('', [Validators.required]),
    surname: new FormControl('', [Validators.required]),
    email: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required]),
    country: new FormControl('', [Validators.required]),
    city: new FormControl('', [Validators.required]),
  });

  register(): void {
    const registration: RecordKeeper = {
      name: this.registrationForm.value.name || "",
      surname: this.registrationForm.value.surname || "",
      email: this.registrationForm.value.email || "",
      password: this.registrationForm.value.password || "",
      city: this.registrationForm.value.city || "",
      country: this.registrationForm.value.country || "",
      confirmPassword: "",
      birthday: "",
      phoneNumber: "",
      jmbg: ""
    };

    if (this.registrationForm.valid) {
      console.log(registration);
      this.authService.register(registration).subscribe({
        next: (response) => {
          this.router.navigate(['home']);
        },
      });
    }
  }
}
