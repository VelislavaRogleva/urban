export class ReportModel {
  location: String;
  title: String;
  text: String;
  images: ImageModel[];
}

export class ImageModel {
  caption: String;
  file: any;
}
