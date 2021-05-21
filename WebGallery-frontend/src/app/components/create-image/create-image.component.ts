import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {Image} from "../images/image";
import {Quality} from "../images/quality";
import {ImageService} from "../../services/image-service/image.service";
import {FormBuilder, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-create-image',
  templateUrl: './create-image.component.html',
  styleUrls: ['./create-image.component.css']
})
export class CreateImageComponent implements OnInit {
  image: Image = new Image();
  qualityList = Object.values(Quality);
  uploadForm: FormGroup;

  constructor(private formBuilder: FormBuilder, private imageService: ImageService) {
  }

  @ViewChild('fileUpload', {static: true})
  input: ElementRef<HTMLInputElement>;

  ngOnInit(): void {
    // this.uploadForm = this.formBuilder.group({
    //   profile: ['']
    // });
  }

  onSubmit() {
    this.imageService.addImage(this.image).subscribe((res) => console.log(res),
      (err) => console.log(err));
  }

  onFileSelect(event: Event) {
    this.image.file = this.input.nativeElement.files?.item(0) as File;
  }
}
