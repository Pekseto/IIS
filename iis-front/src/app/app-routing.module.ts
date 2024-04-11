import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { LoginComponent } from "./login/login.component";
import { JudgeRegistrationComponent } from "./judge-registration/judge-registration.component";
import { PlayerRegistrationComponent } from "./player-registration/player-registration.component";
import { TeamManagerRegistrationComponent } from "./team-manager-registration/team-manager-registration.component";
import { TeamRegistrationComponent } from "./team-registration/team-registration.component";
import { RecordkeeperRegistrationComponent } from "./recordkeeper-registration/recordkeeper-registration.component";
import { HomeComponent } from "./layout/home/home.component";

const routes: Routes = [
    { path: '', component: HomeComponent },
    { path: 'login', component: LoginComponent },
    { path: 'judge-registration', component: JudgeRegistrationComponent },
    { path: 'player-registration', component: PlayerRegistrationComponent },
    { path: 'team-manager-registration', component: TeamManagerRegistrationComponent},
    { path: 'team-registration', component: TeamRegistrationComponent},
    { path: 'recordkeeper-registration', component: RecordkeeperRegistrationComponent },
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }