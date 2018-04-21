import {RouterModule, Routes} from '@angular/router';
import {NgModule} from '@angular/core';

import {LoginComponent} from './components/login/login.component';
import {RegisterComponent} from './components/register/register.component';
import {UsersComponent} from './components/users/users-all/users.component';
import {ReportComponent} from './components/reports/report-add/report.component';
import {AllReportsComponent} from './components/reports/reports-all/all-reports.component';
import {ReportDetailsComponent} from './components/reports/report-details/report-details.component';

import {AuthGuardService as AuthGuard} from './core/services/auth-guard.service'
import {UsersEditComponent} from './components/users/users-edit/users-edit.component';


const routes: Routes = [

  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'users', component: UsersComponent},
  {path: 'report', component: ReportComponent, canActivate: [AuthGuard]},
  {path: 'reports', component: AllReportsComponent},
  {path: 'details/:id', component: ReportDetailsComponent },
  {path: 'users/edit/:id', component: UsersEditComponent },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {}
