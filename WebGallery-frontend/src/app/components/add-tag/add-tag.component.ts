import {Component, OnInit} from '@angular/core';
import {COMMA, ENTER} from '@angular/cdk/keycodes';
import {Tag} from "./tag";
import {TagService} from "../../services/tag-service/tag.service";
import {MatChipInputEvent} from "@angular/material/chips";

@Component({
  selector: 'app-add-tag',
  templateUrl: './add-tag.component.html',
  styleUrls: ['./add-tag.component.css']
})
export class AddTagComponent implements OnInit {
  visible = true;
  selectable = true;
  removable = true;
  addOnBlur = true;
  readonly separatorKeysCodes = [ENTER, COMMA] as const;
  tags: string[] = [];

  constructor(private tagService: TagService) {
  }

  ngOnInit(): void {
    this.tagService.getTags().subscribe((tags) => (this.tags = tags))
  }

  // deleteTag(tag: Tag) {
  //   this.tagService.deleteTag(tag)
  //     .subscribe(() => (this.tags = this.tags.filter(tg => tg.id !== tag.id)));
  // }

  add(event: MatChipInputEvent): void {
    const value = (event.value || '').trim();
    const input = event.input;

    if (value) {
      this.tags.push(value);
      input.value = '';
    }
    // event.chipInput!.clear();
    // event.input!.remove();
  }

  remove(tag: string): void {
    const index = this.tags.indexOf(tag);

    if (index >= 0) {
      this.tags.splice(index, 1);
    }
    // this.tagService.deleteTag(tag)
    //   .subscribe(() => (this.tags = this.tags.filter(tg => tg.id !== tag.id)));
  }
}


