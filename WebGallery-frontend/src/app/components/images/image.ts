import {Quality} from "./quality";

export class Image {
  id: number;
  file: File;
  imageName: string;
  uploadDate: Date;
  imageQuality: Quality;
  imageDescription: string;
  tags: string[];
}
