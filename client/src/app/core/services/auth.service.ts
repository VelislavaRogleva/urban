import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Router} from '@angular/router';

import {LoginModel} from '../models/login.model';
import {RegisterModel} from '../models/register.model';

import * as decode from 'jwt-decode';
import {ToastrService} from 'ngx-toastr';
import {environment} from '../../../environments/environment';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};


@Injectable()
export class AuthService {

  authenticated: boolean = false;
  admin: boolean = false;

  constructor(private router: Router,
              private http: HttpClient,
              private toastrService: ToastrService) {}


  login(username: String, password: String) {

    let url = environment.api_url + '/login';
    let user = new LoginModel(username, password);

    return this.http.post(url, user, {headers: httpOptions.headers, observe: 'response'})
      .subscribe(res => {
          let token = res.headers.get('Authorization').replace('Bearer ', '');

          localStorage.setItem('authToken', token);

          this.authenticated = true;

          if (this.isAdmin()) {
            this.admin = true;
          }

          this.router.navigate(['/']);

        },
        err => {
          this.toastrService.error(
            "Wrong password or username!",
            "Error",
            {
              closeButton: true,
              timeOut: 5000,
              onActivateTick: true
            })
        });
  }

  register(username: String, email: String, password: String) {

    let url = environment.api_url + '/users/register';
    let registerModel = new RegisterModel(username, email, password);


    return this.http.post<RegisterModel>(url, registerModel, httpOptions)
      .subscribe(res => {
        this.router.navigate(['/login']);
        this.toastrService.success("You have registered successfully!")
      });

  }

  logout() {
    this.authenticated = false;
    this.admin = false;
    localStorage.removeItem("authToken");
    this.router.navigate(['/']);
    this.toastrService.success("You have successfully logged out!")
  }

  public getToken(): string {
    return localStorage.getItem('authToken');
  }

  getTokenExpirationDate(): Date {
    const decoded = decode(this.getToken());

    if (decoded.exp === undefined) return null;

    const date = new Date(0);
    date.setUTCSeconds(decoded.exp);
    return date;
  }

  isTokenExpired(): boolean {
    let token = this.getToken();
    if(!token) return true;

    const date = this.getTokenExpirationDate();
    if(date === undefined) return false;
    return !(date.valueOf() > new Date().valueOf());
  }


  public isAuthenticated(): boolean {
    if (this.getToken() === null) return false;
    if (this.isTokenExpired()) {
      this.logout();
      return false;
    }
    return true;
  }

  public isAdmin(): boolean {
    let decoded = decode(this.getToken());

    return decoded['admin'] != null;
  }

}
