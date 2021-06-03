import {Component, OnInit} from '@angular/core';
import {ImageService} from "../../services/image-service/image.service";
import {Image} from "./image";
import {faTimes} from "@fortawesome/free-solid-svg-icons";
import {FormControl} from "@angular/forms";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-images',
  templateUrl: './images.component.html',
  styleUrls: ['./images.component.css']
})
export class ImagesComponent implements OnInit {
  images: Image[] = [];
  allImages: Image[] = [];
  faTimes = faTimes;
  imageNames: string[];
  searchTerm: string;
  control = new FormControl();
  searchText = '';

  constructor(private imageService: ImageService, private http: HttpClient) {
  }

  ngOnInit(): void {
    this.imageService.getImages().subscribe(images => {
      this.images = images;
    });
  }

  deleteImage(image: Image) {
    this.imageService.deleteImage(image)
      .subscribe(() => (this.images = this.images.filter(img => img.id !== image.id)));
  }

  search(value: string): void {
    // this.images = this.allImages.filter((val) => val.imageName.toLowerCase().includes(value));
  }
}
