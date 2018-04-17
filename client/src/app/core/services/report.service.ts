import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Router} from '@angular/router';

import {LoginModel} from '../models/login.model';
import {RegisterModel} from '../models/register.model';

import * as decode from 'jwt-decode';
import {ToastrService} from 'ngx-toastr';
import {environment} from '../../../environments/environment';
import {ReportModel} from '../models/report.model';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'multipart/form-data; boundary=MultipartBoundry'})
};


@Injectable()
export class ReportService {

  constructor(private router: Router,
              private http: HttpClient,
              private toastrService: ToastrService) {}


  addReport(model: ReportModel) {

    let url = environment.api_url + '/reports/add';


    return this.http.post(url, model)
      .subscribe(res => {

      });
  }


  addPicture(file: File) {
    let formdata: FormData = new FormData();

    formdata.append('file', file);

    let url = environment.api_url + '/images/upload';

    return this.http.post(url, formdata, {responseType: 'text'});
  }
}
