import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { LoginComponent } from "./login/login.component";
import { JudgeRegistrationComponent } from "./judge-registration/judge-registration.component";
import { RecordkeeperRegistrationComponent } from "./recordkeeper-registration/recordkeeper-registration.component";
import { HomeComponent } from "./layout/home/home.component";

const routes: Routes = [
    { path: '', component: HomeComponent },
    { path: 'login', component: LoginComponent },
    { path: 'judge-registration', component: JudgeRegistrationComponent },
    { path: 'recordkeeper-registration', component: RecordkeeperRegistrationComponent },
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }