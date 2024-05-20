import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { LoginComponent } from "./login/login.component";
import { JudgeRegistrationComponent } from "./judge-registration/judge-registration.component";
import { PlayerRegistrationComponent } from "./player-registration/player-registration.component";
import { TeamManagerRegistrationComponent } from "./team-manager-registration/team-manager-registration.component";
import { TeamRegistrationComponent } from "./team-registration/team-registration.component";
import { RecordkeeperRegistrationComponent } from "./recordkeeper-registration/recordkeeper-registration.component";
import { HomeComponent } from "./layout/home/home.component";
import { UserInfoComponent } from "./user-info/user-info.component";
import { CoachRegistrationComponent } from "./coach-registration/coach-registration.component";
import { UpcomingGamesComponent } from "./upcoming-games/upcoming-games.component";
import { DelegateRecordkeeperComponent } from "./delegate-recordkeeper/delegate-recordkeeper.component";
import { ScheduleManagmentComponent } from "./schedule-managment/schedule-managment.component";
import { MatchRosterInputComponent } from "./match-roster-input/match-roster-input.component";
import { SemaphoreComponent } from "./semaphore/semaphore.component";
import { MatchRecordkeepingComponent } from "./match-recordkeeping/match-recordkeeping.component";
import { RefereeTeamPickComponent } from "./referee-team-pick/referee-team-pick.component";

const routes: Routes = [
    { path: '', component: HomeComponent },
    { path: 'login', component: LoginComponent },
    { path: 'referee-registration', component: JudgeRegistrationComponent },
    { path: 'player-registration', component: PlayerRegistrationComponent },
    { path: 'team-manager-registration', component: TeamManagerRegistrationComponent},
    { path: 'team-registration', component: TeamRegistrationComponent},
    { path: 'recordkeeper-registration', component: RecordkeeperRegistrationComponent },
    { path: 'user-info', component: UserInfoComponent},
    { path: 'coach-registration', component: CoachRegistrationComponent},
    { path: 'upcoming-matches', component: UpcomingGamesComponent },
    { path: 'delegate-recordkeeper', component: DelegateRecordkeeperComponent},
    { path: 'schedule-managment', component: ScheduleManagmentComponent},
    { path: 'match-roster-input/:matchId', component: MatchRosterInputComponent},
    { path: 'semaphore', component: SemaphoreComponent},
    { path: 'match-recordkeeping/:matchId', component: MatchRecordkeepingComponent},
    { path: 'referee-pick/:id', component: RefereeTeamPickComponent}
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }