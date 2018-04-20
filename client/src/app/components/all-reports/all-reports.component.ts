import {Component, OnInit} from '@angular/core';
import {ReportService} from '../../core/services/report.service';
import {AllReportsModel} from '../../core/models/report.model';

@Component({
  selector: 'app-all-reports',
  templateUrl: './all-reports.component.html'
})
export class AllReportsComponent implements OnInit {

  reports: AllReportsModel[];

  constructor(private reportService: ReportService) {
  }

  ngOnInit() {
    this.getAllReports();
  }

  getAllReports() {
    this.reportService.getAllReports().subscribe(res => {
      this.reports = res;
    });
  }

}
