import {Component, OnInit, Input, Output, EventEmitter} from '@angular/core';
import {Image} from "../images/image";
import {faTimes} from "@fortawesome/free-solid-svg-icons";
import {Observable, Subscriber} from "rxjs";
import {readFile} from "@angular-devkit/build-angular/src/utils/fs";
import {Router} from "@angular/router";

@Component({
  selector: 'app-image-item',
  templateUrl: './image-item.component.html',
  styleUrls: ['./image-item.component.css']
})
export class ImageItemComponent implements OnInit {

  @Input()
  image: Image;
  @Output()
  onDeleteImage: EventEmitter<Image> = new EventEmitter();
  faTimes = faTimes;

  constructor() {
  }

  ngOnInit(): void {
  }

  // editImage(image: Image) {
  //
  // }

  onDelete(image: Image) {
    this.onDeleteImage.emit(image);
  }
}
