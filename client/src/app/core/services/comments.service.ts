import { Injectable } from '@angular/core';
import {environment} from '../../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {CommentAddModel, CommentModel} from '../models/comment.model';

@Injectable()
export class CommentsService {

  constructor(private http: HttpClient) { }

  getComments(id: number) {
    let url = environment.api_url + '/comments/get/' + id;

    return this.http.get<CommentModel[]>(url);
  }

  addComment(id: number, content: any) {
    let url = environment.api_url + '/comments/add';

    let comment: CommentAddModel = new CommentAddModel(content, id);

    return this.http.post(url, comment);
  }
}
