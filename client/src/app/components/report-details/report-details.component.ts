import { Component, OnInit } from '@angular/core';
import {ReportDetailsModel} from '../../core/models/report.model';
import {ReportService} from '../../core/services/report.service';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-report-details',
  templateUrl: './report-details.component.html'
})
export class ReportDetailsComponent implements OnInit {

  report: ReportDetailsModel;

  constructor(private reportService: ReportService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.getReport();
  }

  private getReport() {
    let id =  +this.route.snapshot.paramMap.get("id");
    this.reportService.getReport(id).subscribe(report => {this.report = report});

  }

}
