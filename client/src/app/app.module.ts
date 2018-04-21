import { BrowserModule } from '@angular/platform-browser';
import {ErrorHandler, NgModule} from '@angular/core';
import {AppRoutingModule} from './app-routing.module';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {ToastrModule} from 'ngx-toastr';
import { Ng2CarouselamosModule } from 'ng2-carouselamos';
import {MatDialogModule} from "@angular/material";

import { AppComponent } from './app.component';
import { FooterComponent } from './components/shared/footer/footer.component';
import { HeaderComponent } from './components/shared/header/header.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { UsersComponent } from './components/users/users-all/users.component';
import { UsersEditComponent } from './components/users/users-edit/users-edit.component';
import { ReportComponent } from './components/reports/report-add/report.component';
import { AllReportsComponent } from './components/reports/reports-all/all-reports.component';
import { ReportDetailsComponent } from './components/reports/report-details/report-details.component';
import { CommentsComponent } from './components/comments/comments.component';

import {AuthService} from './core/services/auth.service';
import {AuthGuardService} from './core/services/auth-guard.service';
import {UserService} from './core/services/users.service';
import {ReportService} from './core/services/report.service';
import {ImageUploadService} from './core/services/image-upload.service';
import {CommentsService} from './core/services/comments.service';

import {TokenInterceptor} from './core/interceptors/token.interceptor';
import {GlobalErrorHandler} from './core/error.handler';
import { HomeComponent } from './components/home/home.component';


@NgModule({
  declarations: [
    AppComponent,
    FooterComponent,
    HeaderComponent,
    LoginComponent,
    RegisterComponent,
    UsersComponent,
    ReportComponent,
    AllReportsComponent,
    ReportDetailsComponent,
    CommentsComponent,
    UsersEditComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot(),
    Ng2CarouselamosModule
  ],
  providers: [
    AuthService,
    AuthGuardService,
    UserService,
    ReportService,
    ImageUploadService,
    CommentsService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    },
    {
      provide: ErrorHandler,
      useClass: GlobalErrorHandler
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
