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
