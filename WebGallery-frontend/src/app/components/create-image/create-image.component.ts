import {Component, OnInit} from '@angular/core';
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
  SERVER_URL = "http://localhost:3000/upload";
  uploadForm: FormGroup;

  constructor(private formBuilder: FormBuilder, private imageService: ImageService) {
  }

  ngOnInit(): void {
    this.uploadForm = this.formBuilder.group({
      profile: ['']
    });
  }

  onSubmit() {
    const formData = new FormData();
    formData.append('file', this.image.file);
    this.imageService.addImage(this.image).subscribe((res) => console.log(res),
      (err) => console.log(err));
  }

  // onFileSelect($event: Event) {
  //   if (event.target.files.length > 0) {
  //     const file = event.target.files[0];
  //     this.uploadForm.get('file').setValue(file);
  //   }
  // }
}
