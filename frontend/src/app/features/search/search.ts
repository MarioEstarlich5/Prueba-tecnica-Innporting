import { Component } from '@angular/core';
import { ImageService } from '../../services/image';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';

@Component({
  selector: 'app-search',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MatInputModule,
    MatButtonModule,
    MatCardModule
  ],
  templateUrl: './search.html',
  styleUrl: './search.scss'
})
export class SearchComponent {

  query = '';
  images: any[] = [];
  loading = false;

  currentPage = 0;
  pageSize = 10;

  constructor(private imageService: ImageService) {}

  search(): void {
    if (!this.query) return;

    this.loading = true;
    this.currentPage = 0;
    this.images = [];

    this.loadPage();
  }

  loadPage(): void {
    this.imageService.searchImages(this.query, this.currentPage, this.pageSize).subscribe({
      next: (response) => {
        this.images.push(...response);
        this.loading = false;
      },
      error: () => {
        alert('Error searching images');
        this.loading = false;
      }
    });
  }

  loadMore(): void {
    this.currentPage++;
    this.loadPage();
  }

  removeImage(index: number): void {
    this.images.splice(index, 1);
  }
}
