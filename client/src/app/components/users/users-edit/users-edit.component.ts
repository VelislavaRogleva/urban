import {Component, Input, OnInit} from '@angular/core';
import {UserModel} from '../../../core/models/user.model';
import {ActivatedRoute} from '@angular/router';
import {UserService} from '../../../core/services/users.service';
import {FormBuilder, FormGroup, NgForm, Validators} from '@angular/forms';


@Component({
  selector: 'app-users-edit',
  templateUrl: './users-edit.component.html'
})
export class UsersEditComponent implements OnInit {

  @Input() userModel: UserModel = new UserModel();
  allRoles = ['ADMIN', 'USER'];
  form: FormGroup;

  constructor(private userService: UserService, private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.getUser();

  }
  private getUser() {
    let id =  +this.route.snapshot.paramMap.get("id");
    this.userService.getUser(id).subscribe(user => {
      this.userModel = user;
    })
  }

  save(form: NgForm) {
    let roles = form.controls['roles'].value;
    this.userModel.roles = roles;
    this.userService.editUser(this.userModel);
  }


}
