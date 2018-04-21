import {Component, OnInit} from '@angular/core';

import {UserModel} from '../../../core/models/user.model';
import {UserService} from '../../../core/services/users.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html'
})
export class UsersComponent implements OnInit {

  users: UserModel[];

  constructor(private userService: UserService) {
  }

  getUsers() {
    this.userService.getAllUsers().subscribe(users => {
      console.log(users);
      this.users = users;
    });
  }

  ngOnInit() {
    this.getUsers();
  }

}
