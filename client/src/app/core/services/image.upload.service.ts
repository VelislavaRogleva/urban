import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {environment} from '../../../environments/environment';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'multipart/form-data; boundary=MultipartBoundry'})
};

@Injectable()
export class ImageUploadService {

  constructor(private http: HttpClient) { }

  addPicture(file: File) {
    let formdata: FormData = new FormData();

    formdata.append('file', file);

    let url = environment.api_url + '/images/upload';

    return this.http.post(url, formdata, {responseType: 'text'});
  }

}
