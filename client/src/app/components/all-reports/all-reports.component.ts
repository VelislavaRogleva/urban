import { Component, OnInit } from '@angular/core';
import {ReportService} from '../../core/services/report.service';

@Component({
  selector: 'app-all-reports',
  templateUrl: './all-reports.component.html'
})
export class AllReportsComponent implements OnInit {

  constructor(private reportService: ReportService) { }

  ngOnInit() {
    this.getAllReports();
  }

  getAllReports() {
    this.reportService.getAllReports().subscribe(res => {console.log(res)})
  }

}
