import { BehaviorSubject, Observable, tap, of } from "rxjs";
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
import { RecordKeeper } from "../model/record-keeper.model";


@Injectable({
    providedIn:'root'
  })
export class AuthService {
    user$ = new BehaviorSubject<User>({id: 0, role: "", email: ""});

    constructor(private http: HttpClient,
      private tokenStorage: TokenStorage,
      private router: Router) { }

      login(login: Login): Observable<AuthenticationResponse> {
        return this.http
          .post<AuthenticationResponse>(environment.apiHost + 'users/login', login)
          .pipe(
            tap((authenticationResponse) => {
              this.tokenStorage.saveAccessToken(authenticationResponse.access_token);
              this.setUser();
            })
          );
      }

      register(registration: Registration): Observable<AuthenticationResponse> {
        return this.http
        .post<AuthenticationResponse>(environment.apiHost + 'users/register', registration)
      }

      registerRecordKeeper(registration: RecordKeeper): Observable<AuthenticationResponse>{
        return this.http.post<AuthenticationResponse>(environment.apiHost + 'record-keeper/register', registration);
      }

      logout(): void {
        this.router.navigate(['']).then(_ => {
          this.tokenStorage.clear();
          this.user$.next({id: 0, role: "", email: ""});
          }
        );
      }

      private setUser(): void {
        const jwtHelperService = new JwtHelperService();
        const accessToken = this.tokenStorage.getAccessToken() || "";
        const user: User = {
          id: +jwtHelperService.decodeToken(accessToken).id,
          role: jwtHelperService.decodeToken(accessToken).role,
          email: jwtHelperService.decodeToken(accessToken).sub,
        };
        this.user$.next(user);
      }

      getUser(): Observable<User> {
        const jwtHelperService = new JwtHelperService();
        const accessToken = this.tokenStorage.getAccessToken() || '';
        
        const decodedToken = jwtHelperService.decodeToken(accessToken);
          const user: User = {
            id: +decodedToken.id,
            role: decodedToken.role,
            email: decodedToken.sub,
          };
          return of(user);
         
      }
    
    
}