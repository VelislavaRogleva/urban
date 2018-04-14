import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

import {Observable} from 'rxjs/Observable';
import {of} from 'rxjs/observable/of';
import {catchError} from 'rxjs/operators';

import {UserModel} from '../models/user.model';
import {environment} from '../../../environments/environment';

@Injectable()
export class UserService {

  constructor(private http: HttpClient) {}

  getAllUsers(): Observable<UserModel[]> {
    const url = environment.api_url + '/users/all';
    return this.http.get<UserModel[]>(url);
  }

}
