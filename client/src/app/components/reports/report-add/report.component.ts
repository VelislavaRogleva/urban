import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, FormArray, Validators} from '@angular/forms';

import {ImageModel, ReportModel} from '../../../core/models/report.model';
import {ReportService} from '../../../core/services/report.service';
import {ImageUploadService} from '../../../core/services/image-upload.service';
import {Observable} from 'rxjs/Observable';
import { forkJoin } from 'rxjs/observable/forkJoin';
import { of } from 'rxjs/observable/of';

@Component({
  selector: 'app-report',
  templateUrl: './report.component.html'
})
export class ReportComponent implements OnInit {

  form: FormGroup;
  files: File[] = [];

  constructor(private fb: FormBuilder,
              private reportService: ReportService,
              private imageUploadService: ImageUploadService) {

  }

  ngOnInit() {
    this.form = this.fb.group({
      location: ['', Validators.required],
      title: ['', Validators.required],
      content: ['', Validators.required],
      images: this.fb.array([this.initImages()])
    });
  }


  submitData() {
    let data = this.form.value;
    let reportModel = new ReportModel();
    reportModel.location = data.location;
    reportModel.title = data.title;
    reportModel.content = data.content;

    let observableArray: any = [];
    this.files.forEach((file: any, index: any) => {
      observableArray.push(this.imageUploadService.addPicture(file));
    });

    forkJoin(observableArray)
      .subscribe(fileIds => {
        //this.myFunction(data[0], data[1]...);
        let images: ImageModel[] = [];
        for (let i in fileIds) {
          let img = new ImageModel(data.images[i].caption, fileIds[i]);
          images.push(img);
        }

        reportModel.images = images;
        this.reportService.addReport(reportModel);
      });
  }

  onFileChange(event, i) {
    if (event.target.files.length > 0) {
      let file = event.target.files[0];
      this.files.push(file);
    }
  }

  initImages() {
    return this.fb.group({
      caption: [''],
      file: ['']
    });
  }

  addImage() {
    const control = <FormArray>this.form.controls['images'];
    control.push(this.initImages());
  }

}
