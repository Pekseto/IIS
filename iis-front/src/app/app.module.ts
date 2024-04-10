import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { AppRoutingModule } from './app-routing.module';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { JwtInterceptor } from './jwt/jwt.interceptor';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { JudgeRegistrationComponent } from './judge-registration/judge-registration.component';
import { PlayerRegistrationComponent } from './player-registration/player-registration.component';
import { TeamManagerRegistrationComponent } from './team-manager-registration/team-manager-registration.component';
import { TeamRegistrationComponent } from './team-registration/team-registration.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    JudgeRegistrationComponent,
    PlayerRegistrationComponent,
    TeamManagerRegistrationComponent,
    TeamRegistrationComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: JwtInterceptor,
      multi: true,
    },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
