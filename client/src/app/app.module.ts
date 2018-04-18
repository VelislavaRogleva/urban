import { BrowserModule } from '@angular/platform-browser';
import {ErrorHandler, NgModule} from '@angular/core';
import {AppRoutingModule} from './app-routing.module';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {ToastrModule} from 'ngx-toastr';

import { AppComponent } from './app.component';
import { FooterComponent } from './components/shared/footer/footer.component';
import { HeaderComponent } from './components/shared/header/header.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { UsersComponent } from './components/users/users.component';
import { ReportComponent } from './components/report/report.component';
import { AllReportsComponent } from './components/all-reports/all-reports.component';
import { ReportDetailsComponent } from './components/report-details/report-details.component';

import {AuthService} from './core/services/auth.service';
import {AuthGuardService} from './core/services/auth-guard.service';
import {UserService} from './core/services/users.service';
import {ReportService} from './core/services/report.service';
import {ImageUploadService} from './core/services/image-upload.service';

import {TokenInterceptor} from './core/interceptors/token.interceptor';
import {GlobalErrorHandler} from './core/error.handler';

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
    ReportDetailsComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot()
  ],
  providers: [
    AuthService,
    AuthGuardService,
    UserService,
    ReportService,
    ImageUploadService,
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
