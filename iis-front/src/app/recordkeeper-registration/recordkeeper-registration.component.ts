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

  register(): void {
    const registration: RecordKeeper = {
      name: this.registrationForm.value.name || "",
      surname: this.registrationForm.value.surname || "",
      email: this.registrationForm.value.email || "",
      password: this.registrationForm.value.password || "",
      birthday: this.registrationForm.value.birthday || "",
      jmbg: this.registrationForm.value.jmbg || "",
      phoneNumber: this.registrationForm.value.phoneNumber || "",
      city: this.registrationForm.value.city || "",
      country: this.registrationForm.value.country || "",
    };

    if (this.registrationForm.valid) {
      console.log(registration);
      this.authService.registerRecordKeeper(registration).subscribe({
        next: (response) => {
          this.router.navigate(['']);
        },
      });
    }
  }
}
