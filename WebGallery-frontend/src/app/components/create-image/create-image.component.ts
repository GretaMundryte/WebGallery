import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {Image} from "../images/image";
import {Quality} from "../images/quality";
import {ImageService} from "../../services/image-service/image.service";
import {FormBuilder} from "@angular/forms";
import {ActivatedRoute} from "@angular/router";
import {Observable, Subscriber} from "rxjs";

@Component({
  selector: 'app-create-image',
  templateUrl: './create-image.component.html',
  styleUrls: ['./create-image.component.css']
})
export class CreateImageComponent implements OnInit {
  image: Image = new Image();
  qualityList = Object.values(Quality);
  imageUrl: File;
  imgUrl: string;

  constructor(private formBuilder: FormBuilder, private imageService: ImageService, private router: ActivatedRoute) {
  }

  @ViewChild('fileUpload', {static: true})
  input: ElementRef<HTMLInputElement>;

  ngOnInit(): void {
    if (this.router.snapshot.params.id) {
      this.imageService.getCurrentData(this.router.snapshot.params.id).subscribe((result) => {
        this.image = result;
        this.imageUrl = this.image.file;
      })
    }
  }

  onSubmit() {
    console.log(this.image);
    if (!this.image.id) {
      this.imageService.addImage(this.image).subscribe((res) => console.log(res),
        (err) => console.log(err));
    } else {
      this.imageService.updateImage(this.router.snapshot.params.id, this.image).subscribe((result) =>
        console.log(result));
    }
  }

  onFileSelect($event: Event) {
    this.image.file = this.input.nativeElement.files?.item(0) as File;

    // @ts-ignore
    const file = ($event.target as HTMLInputElement).files[0];
    console.log(file);
    this.convertToBase64(file);
  }

  convertToBase64(file: File) {
    const observable = new Observable((subscriber: Subscriber<any>) => {
      this.readFile(file, subscriber)
    });
    observable.subscribe((data) => {
      this.imgUrl = data;
    })
  }

  readFile(file: File, subscriber: Subscriber<any>) {
    const fileReader = new FileReader();
    fileReader.readAsDataURL(file);
    fileReader.onload = () => {
      subscriber.next(fileReader.result);
      subscriber.complete();
    }
    fileReader.onerror = (error) => {
      subscriber.error(error);
      subscriber.complete();
    }
  }
}
