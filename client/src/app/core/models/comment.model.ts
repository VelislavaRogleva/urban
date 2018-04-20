export class CommentModel {
  content: String;
  username: String;
  publishedOn: Date;
}

export class CommentAddModel {
  content: String;
  reportId: Number;


  constructor(content: String, reportId: Number) {
    this.content = content;
    this.reportId = reportId;
  }
}
