import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, FormArray, Validators} from '@angular/forms';

import {ReportModel} from '../../core/models/report.model';
import {ReportService} from '../../core/services/report.service';
import {ImageUploadService} from '../../core/services/image.upload.service';

@Component({
  selector: 'app-report',
  templateUrl: './report.component.html'
})
export class ReportComponent implements OnInit {

  form: FormGroup;

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


  submitData(model: ReportModel) {
    console.log(model);
    this.reportService.addReport(model);
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


  onFileChange(event, i) {
    if(event.target.files.length > 0) {
      let file = event.target.files[0];

      this.imageUploadService.addPicture(file).subscribe(res => {
        this.form.controls.images.value[i].file = res; });
      }
  }






}
