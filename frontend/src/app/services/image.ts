import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ImageService {

  private readonly API_URL = 'http://localhost:8080/api/images';

  constructor(private http: HttpClient) {}

  searchImages(text: string): Observable<any> {
    return this.http.get<any>(`${this.API_URL}/search?text=${text}`);
  }

  getImageDetail(id: string): Observable<any> {
    return this.http.get<any>(`${this.API_URL}/${id}`);
  }
}
