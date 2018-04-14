import { Component, OnInit } from '@angular/core';

import {UserService} from '../../core/services/users.service';
import {UserModel} from '../../core/models/user.model';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html'
})
export class UsersComponent implements OnInit {

  users: UserModel[];

  constructor(private userService: UserService) { }

  getUsers() {
    this.userService.getAllUsers().subscribe(users => this.users = users);
  }

  ngOnInit() {
    this.getUsers();
  }

}
