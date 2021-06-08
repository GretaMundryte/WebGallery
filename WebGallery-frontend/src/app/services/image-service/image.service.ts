import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from 'rxjs'
import {Image} from "../../components/images/image";

@Injectable({
  providedIn: 'root'
})
export class ImageService {
  private apiUrl = 'http://localhost:8080/api/images';

  constructor(private http: HttpClient) {
  }

  getImages(): Observable<Image[]> {
    return this.http.get<Image[]>(this.apiUrl);
  }

  addImage(newImage: Image): Observable<void> {
    const formData = new FormData();
    formData.append('file', newImage.file);
    formData.append('imageInfo', new Blob([JSON.stringify(newImage)], {
      type: 'application/json'
    }));
    return this.http.post<void>(this.apiUrl, formData, {headers: {enctype: 'multipart/form-data'}})
  }

  updateImage(id: number, updatedImage: Image): Observable<any> {
    const url = this.apiUrl + "/edit/" + id;
    const formData = new FormData();
    formData.append('multipartFile', updatedImage.file);
    formData.append('imageInfo', new Blob([JSON.stringify(updatedImage)], {
      type: 'application/json'
    }));
    return this.http.put<void>(url, formData, {headers: {enctype: 'multipart/form-data'}});
  }

  deleteImage(image: Image): Observable<Image> {
    const url = `${this.apiUrl}/${image.id}`;
    return this.http.delete<Image>(url);
  }

  getCurrentData(id: number): Observable<Image> {
    return this.http.get<Image>(`${this.apiUrl}/` + id);
  }

  getImageBySearch(keyword: string): Observable<any> {
    if (keyword) {
      return this.http.get(`${this.apiUrl}/search/${keyword}`);
    } else {
      return this.http.get<Image[]>(this.apiUrl);
    }
  }
}
