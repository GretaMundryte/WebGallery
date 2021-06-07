import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {Image} from "../images/image";
import {Quality} from "../images/quality";
import {ImageService} from "../../services/image-service/image.service";
import {FormBuilder} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {Observable, Subscriber} from "rxjs";
import {Tag} from "../images/tag";
import {COMMA, ENTER} from "@angular/cdk/keycodes";
import {MatChipInputEvent} from "@angular/material/chips";

@Component({
  selector: 'app-create-image',
  templateUrl: './create-image.component.html',
  styleUrls: ['./create-image.component.css']
})
export class CreateImageComponent implements OnInit {
  image: Image = new Image();
  qualityList = Object.values(Quality);
  imgUrl: string;

  visible = true;
  selectable = true;
  removable = true;
  addOnBlur = true;
  readonly separatorKeysCodes = [ENTER, COMMA] as const;
  tagsToString: string;

  constructor(private formBuilder: FormBuilder, private imageService: ImageService, private activatedRoute: ActivatedRoute, private router: Router) {
  }

  @ViewChild('fileUpload', {static: true})
  input: ElementRef<HTMLInputElement>;

  ngOnInit(): void {
    if (this.activatedRoute.snapshot.params.id) {
      this.imageService.getCurrentData(this.activatedRoute.snapshot.params.id).subscribe((result) => {
        this.image = result;
      })
    }
  }

  onSubmit() {
    const imageObservable = this.image.id
      ? this.imageService.updateImage(this.activatedRoute.snapshot.params.id, this.image)
      : this.imageService.addImage(this.image);
    imageObservable.subscribe(() => this.router.navigate(['/listImages']));
  }

  onFileSelect($event: Event) {
    this.image.file = this.input.nativeElement.files?.item(0) as File;

    // @ts-ignore
    const file = ($event.target as HTMLInputElement).files[0];
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

  addTag(event: MatChipInputEvent): void {
    const value = (event.value || '').trim();
    const input = event.input;
    if (value) {
      // this.image.tags.push({ tag: value });
      this.image.tags.push(new Tag(value));
      input.value = '';
    }
  }

  removeTag(tag: Tag): void {
    const index = this.image.tags.indexOf(tag);
    if (index >= 0) {
      this.image.tags.splice(index, 1);
    }
  }
}
