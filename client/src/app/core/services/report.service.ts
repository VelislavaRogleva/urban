import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Router} from '@angular/router';

import {ToastrService} from 'ngx-toastr';
import {environment} from '../../../environments/environment';
import {AllReportsModel, ReportModel} from '../models/report.model';

@Injectable()
export class ReportService {

  constructor(private router: Router,
              private http: HttpClient,
              private toastrService: ToastrService) {}


  addReport(model: ReportModel) {

    let url = environment.api_url + '/reports/add';

    return this.http.post(url, model)
      .subscribe();
  }

  getAllReports() {

    let url = environment.api_url + '/reports/all';

    return this.http.get<AllReportsModel[]>(url);

  }


}
