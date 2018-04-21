import {Component, OnInit} from '@angular/core';

import {UserModel} from '../../../core/models/user.model';
import {UserService} from '../../../core/services/users.service';
import {Observable} from 'rxjs/Observable';
import {share} from 'rxjs/operators';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html'
})
export class UsersComponent implements OnInit {

  users: Observable<UserModel[]>;

  constructor(private userService: UserService) {
  }

  getUsers() {
    this.users = this.userService.getAllUsers().pipe(share())
  }

  ngOnInit() {
    this.getUsers();
  }

  deactivateAccount(id) {
    this.userService.deactivateAccount(id).subscribe(res => {
      this.getUsers();
    });
  }

  activateAccount(id) {
    this.userService.activateAccount(id).subscribe(res => {
      this.getUsers();
    });
  }
}
