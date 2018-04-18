import {RouterModule, Routes} from '@angular/router';
import {NgModule} from '@angular/core';

import {LoginComponent} from './components/login/login.component';
import {RegisterComponent} from './components/register/register.component';
import {UsersComponent} from './components/users/users.component';
import {ReportComponent} from './components/report/report.component';
import {AllReportsComponent} from './components/all-reports/all-reports.component';
import {ReportDetailsComponent} from './components/report-details/report-details.component';

import {AuthGuardService as AuthGuard} from './core/services/auth-guard.service'


const routes: Routes = [

  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'users', component: UsersComponent},
  {path: 'report', component: ReportComponent, canActivate: [AuthGuard]},
  {path: 'reports', component: AllReportsComponent},
  {path: 'details/:id', component: ReportDetailsComponent },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {}
