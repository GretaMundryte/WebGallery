import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from 'rxjs'
import {Image} from "../../components/images/image";

@Injectable({
  providedIn: 'root'
})
export class ImageService {
  // private apiUrl = 'http://localhost:5000/images';
  private apiUrl = 'http://localhost:8080/api/images';

  constructor(private http: HttpClient) {
  }

  getImages(): Observable<Image[]> {
    return this.http.get<Image[]>(this.apiUrl)
  }

  addImage(newImage: Image) {
    const formData = new FormData();
    formData.append('file', newImage.file);
    formData.append('imageInfo', new Blob([JSON.stringify(newImage)], {
      type: 'application/json'
    }))

    // (this.url, data, { headers: { enctype: 'multipart/form-data' } })

    console.log(formData, newImage);
    return this.http.post<void>(this.apiUrl, formData, {headers: {enctype: 'multipart/form-data'}});
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
