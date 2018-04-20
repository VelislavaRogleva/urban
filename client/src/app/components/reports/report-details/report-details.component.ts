import { Component, OnInit } from '@angular/core';
import {ReportDetailsModel} from '../../../core/models/report.model';
import {ReportService} from '../../../core/services/report.service';
import {ActivatedRoute} from '@angular/router';
import {share} from 'rxjs/operators';
import {Observable} from 'rxjs/Observable';

@Component({
  selector: 'app-report-details',
  templateUrl: './report-details.component.html'
})
export class ReportDetailsComponent implements OnInit {

  report: Observable<ReportDetailsModel>;

  constructor(private reportService: ReportService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.getReport();
  }

  private getReport() {
    let id =  +this.route.snapshot.paramMap.get("id");
    this.report = this.reportService.getReport(id).pipe(share())
  }

}
