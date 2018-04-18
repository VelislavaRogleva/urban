export class ReportModel {
  location: String;
  title: String;
  content: String;
  images: ImageModel[];
}

export class ImageModel {
  caption: String;
  file: any;
}

export class AllReportsModel {
  id: Number;
  title: String;
  content: String;
  username: String;
  publishedOn: Date;
}
