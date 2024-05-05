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
import { RecordkeeperRegistrationComponent } from './recordkeeper-registration/recordkeeper-registration.component';
import { NavbarComponent } from './layout/navbar/navbar.component';
import { HomeComponent } from './layout/home/home.component';
import { UserInfoComponent } from './user-info/user-info.component';
import { CoachRegistrationComponent } from './coach-registration/coach-registration.component';
import { UpcomingGamesComponent } from './upcoming-games/upcoming-games.component';
import { DelegateRecordkeeperComponent } from './delegate-recordkeeper/delegate-recordkeeper.component';
import { MatchRosterInputComponent } from './match-roster-input/match-roster-input.component';
import { SemaphoreComponent } from './semaphore/semaphore.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    JudgeRegistrationComponent,
    PlayerRegistrationComponent,
    TeamManagerRegistrationComponent,
    TeamRegistrationComponent,
    RecordkeeperRegistrationComponent,
    NavbarComponent,
    HomeComponent,
    UserInfoComponent,
    CoachRegistrationComponent,
    UpcomingGamesComponent,
    DelegateRecordkeeperComponent,
    MatchRosterInputComponent,
    SemaphoreComponent,
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
