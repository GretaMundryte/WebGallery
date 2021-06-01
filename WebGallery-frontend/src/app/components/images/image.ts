import {Quality} from "./quality";
import {Tag} from "./tag";

export class Image {
  id: number;
  file: File;
  imageName: string;
  uploadDate: Date;
  imageQuality: Quality;
  imageDescription: string;
  tags: Tag[] = [];
}
