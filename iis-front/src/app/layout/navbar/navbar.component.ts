import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model/user.model';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  user!: User;
  constructor(private authService: AuthService) { }

  ngOnInit(): void {
    this.user = this.authService.user$.getValue();
  }

  logout(): void {
    this.authService.logout();
  }

}
