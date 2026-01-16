import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ImageService {

  private readonly API_URL = 'http://localhost:8080/api/images';

  constructor(private http: HttpClient) {}

  searchImages(query: string, page: number = 0, size: number = 10): Observable<any> {
    return this.http.get<any>(`${this.API_URL}/search`, {
      params: {
        query: query,
        page: page,
        size: size
      }
    });
  }

  getImageDetail(id: string): Observable<any> {
    return this.http.get<any>(`${this.API_URL}/${id}`);
  }
}
