import {Injectable} from '@angular/core';
import {Tag} from "../../components/add-tag/tag";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class TagService {
  private apiUrl = 'http://localhost:5000/tags';

  constructor(private http: HttpClient) {
  }

  getTags(): Observable<string[]> {
    return this.http.get<string[]>(this.apiUrl)
  }

  // addTag(tag: Tag): Observable<Tag> {
  //   const url = `${this.apiUrl}/${tag.id}`;
  //   return this.http.post(this.apiUrl, createBody, );
  // }

  deleteTag(tag: Tag): Observable<Tag> {
    const url = `${this.apiUrl}/${tag.id}`;
    return this.http.delete<Tag>(url);
  }
}
