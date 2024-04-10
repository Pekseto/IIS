import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { LoginComponent } from "./login/login.component";
import { JudgeRegistrationComponent } from "./judge-registration/judge-registration.component";
import { PlayerRegistrationComponent } from "./player-registration/player-registration.component";
import { TeamManagerRegistrationComponent } from "./team-manager-registration/team-manager-registration.component";
import { TeamRegistrationComponent } from "./team-registration/team-registration.component";

const routes: Routes = [
    { path: 'login', component: LoginComponent },
    { path: 'judge-registration', component: JudgeRegistrationComponent },
    { path: 'player-registration', component: PlayerRegistrationComponent },
    { path: 'team-manager-registration', component: TeamManagerRegistrationComponent},
    { path: 'team-registration', component: TeamRegistrationComponent},
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
  })
  export class AppRoutingModule { }