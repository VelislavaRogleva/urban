export class ReportModel {
  location: String;
  title: String;
  content: String;
  images: ImageModel[];
}

export class ImageModel {
  caption: String;
  file: any;


  constructor(caption: String, file: any) {
    this.caption = caption;
    this.file = file;
  }
}

export class ImageViewModel {
  caption: String;
  url: String;
}

export class AllReportsModel {
  id: Number;
  title: String;
  content: String;
  username: String;
  publishedOn: Date;
}

export class ReportDetailsModel {
  id: Number;
  title: String;
  content: String;
  username: String;
  publishedOn: Date;
  images: ImageViewModel[];
}
