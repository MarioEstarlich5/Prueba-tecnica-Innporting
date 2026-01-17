import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Image {
  id: string;
  title: string;
  thumbnailUrl: string;
  imageUrl: string;
  author: string;
}

export interface SearchResponse {
  page: number;
  totalPages: number;
  images: Image[];
}

@Injectable({
  providedIn: 'root'
})
export class ImageService {

  private readonly API_URL = 'http://localhost:8080/api/images';

  constructor(private http: HttpClient) {}

  searchImages(query: string, page: number = 1, size: number = 10): Observable<SearchResponse> {
    return this.http.get<SearchResponse>(`${this.API_URL}/search`, {
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
