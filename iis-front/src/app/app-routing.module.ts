import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { LoginComponent } from "./login/login.component";
import { JudgeRegistrationComponent } from "./judge-registration/judge-registration.component";

const routes: Routes = [
    { path: 'login', component: LoginComponent },
    { path: 'judge-registration', component: JudgeRegistrationComponent },
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
  })
  export class AppRoutingModule { }