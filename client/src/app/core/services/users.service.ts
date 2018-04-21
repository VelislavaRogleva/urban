import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

import {Observable} from 'rxjs/Observable';
import {of} from 'rxjs/observable/of';
import {catchError} from 'rxjs/operators';

import {UserModel} from '../models/user.model';
import {environment} from '../../../environments/environment';
import {Router} from '@angular/router';

@Injectable()
export class UserService {

  constructor(private http: HttpClient, private router: Router) {}

  getAllUsers(): Observable<UserModel[]> {
    const url = environment.api_url + '/users/all';
    return this.http.get<UserModel[]>(url);
  }

  getUser(id: number) {
    const url = environment.api_url + '/users/edit/' + id;
    return this.http.get<UserModel>(url);
  }

  editUser(userModel: UserModel) {
    const url = environment.api_url + '/users/edit';
    return this.http.post(url, userModel).subscribe(res => {
      this.router.navigate(['/users']);
    });

  }

  deactivateAccount(id: any) {
    const url = environment.api_url + '/users/deactivate/' + id;
    return this.http.post(url, null);
  }

  activateAccount(id: any) {
    const url = environment.api_url + '/users/activate/' + id;
    return this.http.post(url, null);
  }
}
