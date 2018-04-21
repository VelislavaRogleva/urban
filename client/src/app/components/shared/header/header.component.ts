import { Component, OnInit } from '@angular/core';
import {AuthService} from '../../../core/services/auth.service';
declare var require: any;

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})

export class HeaderComponent implements OnInit {

  public LOGO = require("./assets/logo.png");

  constructor(public authService: AuthService) { }

  ngOnInit() {

  }

}
