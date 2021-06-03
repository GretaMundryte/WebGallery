import {Component, OnInit, Input, Output, EventEmitter} from '@angular/core';
import {Image} from "../images/image";
import {faTimes} from "@fortawesome/free-solid-svg-icons";

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

  get tagNames() {
    return this.image?.tags?.map(tag => tag.tag);
  }

  constructor() {
  }

  ngOnInit(): void {
  }

  onDelete(image: Image) {
    this.onDeleteImage.emit(image);
  }
}
