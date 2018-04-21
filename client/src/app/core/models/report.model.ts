import {ImageModel, ImageViewModel} from './image.model';

export class ReportModel {
  location: String;
  title: String;
  content: String;
  images: ImageModel[];
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
