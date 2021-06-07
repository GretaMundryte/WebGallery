import {Component, OnInit} from '@angular/core';
import {ImageService} from "../../services/image-service/image.service";
import {Image} from "./image";
import {faTimes} from "@fortawesome/free-solid-svg-icons";
import {faSearch} from "@fortawesome/free-solid-svg-icons";

@Component({
  selector: 'app-images',
  templateUrl: './images.component.html',
  styleUrls: ['./images.component.css']
})
export class ImagesComponent implements OnInit {
  images: Image[] = [];
  faTimes = faTimes;
  faSearch = faSearch;
  keyword: string = "";

  constructor(private imageService: ImageService) {
  }

  ngOnInit(): void {
    this.imageService.getImages().subscribe(img => {
      this.images = img;
    });
  }

  deleteImage(image: Image) {
    this.imageService.deleteImage(image)
      .subscribe(() => (this.images = this.images.filter(img => img.id !== image.id)));
  }

  onSearch() {
    this.imageService.getImageBySearch(this.keyword).subscribe(img => {
      this.images = img
    });
  }
}
