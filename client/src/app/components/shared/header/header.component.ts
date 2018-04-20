import { Component, OnInit } from '@angular/core';
import {AuthService} from '../../../core/services/auth.service';
import {Observable} from 'rxjs/Observable';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html'
})
export class HeaderComponent implements OnInit {

  public LOGO = require("./assets/logo.png");

  constructor(public authService: AuthService) { }

  ngOnInit() {

  }

}
