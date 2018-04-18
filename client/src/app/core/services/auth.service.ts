import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Router} from '@angular/router';

import {LoginModel} from '../models/login.model';
import {RegisterModel} from '../models/register.model';

import * as decode from 'jwt-decode';
import {ToastrService} from 'ngx-toastr';
import {environment} from '../../../environments/environment';
import {BehaviorSubject} from 'rxjs/BehaviorSubject';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};


@Injectable()
export class AuthService {

  public isLoggedIn: boolean;
  public isAdmin: boolean;

  constructor(private router: Router,
              private http: HttpClient,
              private toastrService: ToastrService) {

    this.isLoggedIn = this.isAuthenticated();

  }


  login(username: String, password: String) {

    let url = environment.api_url + '/login';
    let user = new LoginModel(username, password);

    return this.http.post(url, user, {headers: httpOptions.headers, observe: 'response'})
      .subscribe(res => {
          let token = res.headers.get('Authorization').replace('Bearer ', '');

          localStorage.setItem('authToken', token);

          this.isLoggedIn = true;
          if (this.checkIfAdmin()) {
            this.isAdmin = true;
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
    localStorage.removeItem("authToken");
    this.isLoggedIn = false;
    this.isAdmin = false;
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

  public checkIfAdmin(): boolean {
    if (this.getToken() === null) return false;
    let decoded = decode(this.getToken());

    return decoded['admin'] != null;
  }

}
