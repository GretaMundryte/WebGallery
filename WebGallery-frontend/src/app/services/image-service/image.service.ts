import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from 'rxjs'
import {Image} from "../../components/images/image";

@Injectable({
  providedIn: 'root'
})
export class ImageService {
  private apiUrl = 'http://localhost:5000/images';

  constructor(private http: HttpClient) {
  }

  getImages(): Observable<Image[]> {
    return this.http.get<Image[]>(this.apiUrl)
  }

  addImage(newImage: Image) {
    return this.http.post(this.apiUrl, newImage)
  }

  updateImage(id: number, updatedImage: Image) {
    const endpointURl = this.apiUrl + "/" + id;
    return this.http.put(endpointURl, updatedImage);
  }

  deleteImage(image: Image): Observable<Image> {
    const url = `${this.apiUrl}/${image.id}`;
    return this.http.delete<Image>(url);
  }
}
