import { Component } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Registration } from '../model/registration.model';

@Component({
  selector: 'app-recordkeeper-registration',
  templateUrl: './recordkeeper-registration.component.html',
  styleUrls: ['./recordkeeper-registration.component.css']
})
export class RecordkeeperRegistrationComponent {
  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  registrationForm = new FormGroup({
    name: new FormControl('', [Validators.required]),
    surname: new FormControl('', [Validators.required]),
    email: new FormControl('', [Validators.required]),
    username: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required]),
  });

  register(): void {
    const registration: Registration = {
      name: this.registrationForm.value.name || "",
      surname: this.registrationForm.value.surname || "",
      email: this.registrationForm.value.email || "",
      password: this.registrationForm.value.password || "",
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
