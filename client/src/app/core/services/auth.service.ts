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
    this.router.navigate(['/']);
    this.toastrService.success("You have successfully logged out!")
  }

  public getToken(): string {
    return localStorage.getItem('authToken');
  }


  public isAuthenticated(): boolean {
    let token = this.getToken();
    return token != null;
  }

  public isAdmin(): boolean {
    let decoded = decode(this.getToken());

    return decoded['admin'] != null;
  }

}
