import {Component, OnInit} from '@angular/core';
import {CommentsService} from '../../core/services/comments.service';
import {ActivatedRoute} from '@angular/router';
import {CommentModel} from '../../core/models/comment.model';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AuthService} from '../../core/services/auth.service';


@Component({
  selector: 'app-comments',
  templateUrl: './comments.component.html'
})
export class CommentsComponent implements OnInit {

  public comments: CommentModel[];
  form: FormGroup;

  constructor(private fb: FormBuilder,
              private commentService: CommentsService,
              private route: ActivatedRoute,
              public authService: AuthService) {
  }

  ngOnInit() {
    this.form = this.fb.group({
      content: ['', Validators.required]
    });
  }


  private getComments() {
    let id = +this.route.snapshot.paramMap.get('id');
    this.commentService.getComments(id).subscribe(res => {
      this.comments = res
    });
  }

  public submitData() {
    let id = +this.route.snapshot.paramMap.get('id');
    let content = this.form.value.content;
    this.commentService.addComment(id, content).subscribe(res => {
      this.form.reset();
      this.getComments();
    });
  }
}
