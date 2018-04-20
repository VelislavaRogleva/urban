import { Component, OnInit } from '@angular/core';
import {AuthService} from '../../../core/services/auth.service';

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
