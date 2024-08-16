import { Routes } from '@angular/router';
import {HomePageComponent} from "./components/home-page/home-page.component";
import {DashboardComponent} from "./components/dashboard/dashboard.component";
import {SignupComponent} from "./components/signup/signup.component";
import {LoginComponent} from "./components/login/login.component";

export const routes: Routes = [
  {path: '', redirectTo: '/home', pathMatch: 'full'},
  {path: 'home', component: HomePageComponent},
  {path: 'dashboard', component: DashboardComponent},
  {path: 'signup', component: SignupComponent},
  {path: 'login', component: LoginComponent},
];
