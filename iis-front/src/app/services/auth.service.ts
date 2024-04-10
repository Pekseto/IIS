import { BehaviorSubject, Observable, tap } from "rxjs";
import { User } from "../model/user.model";
import { HttpClient } from "@angular/common/http";
import { TokenStorage } from "../jwt/token.service";
import { Router } from "@angular/router";
import { Login } from "../model/login.model";
import { environment } from "src/env/environment";
import { JwtHelperService } from '@auth0/angular-jwt';
import { AuthenticationResponse } from "../responses/authentication-response.model";
import { Injectable } from "@angular/core";
import { Registration } from "../model/registration.model";

@Injectable({
    providedIn:'root'
  })
export class AuthService {
    user$ = new BehaviorSubject<User>({email: "", id: 0,});

    constructor(private http: HttpClient,
      private tokenStorage: TokenStorage,
      private router: Router) { }

      login(login: Login): Observable<AuthenticationResponse> {
        return this.http
          .post<AuthenticationResponse>(environment.apiHost + 'users/login', login)
          .pipe(
            tap((authenticationResponse) => {
              this.tokenStorage.saveAccessToken(authenticationResponse.accessToken);
              this.setUser();
            })
          );
      }

      register(registration: Registration): Observable<AuthenticationResponse> {
        return this.http
        .post<AuthenticationResponse>(environment.apiHost + 'users', registration)
        .pipe(
          tap((authenticationResponse) => {
            this.tokenStorage.saveAccessToken(authenticationResponse.accessToken);
            //this.setUser();
          })
        );
      }

      private setUser(): void {
        const jwtHelperService = new JwtHelperService();
        const accessToken = this.tokenStorage.getAccessToken() || "";
        const user: User = {
          id: +jwtHelperService.decodeToken(accessToken).id,
          email: jwtHelperService.decodeToken(accessToken).email[
            'http://schemas.microsoft.com/ws/2008/06/identity/claims/role'
          ],
        };
        this.user$.next(user);
      }
}