import { Component, ChangeDetectorRef, HostListener } from '@angular/core';
import { ImageService, Image } from '../../services/image';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { Router } from '@angular/router';

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
  images: Image[] = [];
  loading = false;
  isLoadingMore = false;
  page = 1;
  totalPages = 1;

  constructor(
    private imageService: ImageService,
    private router: Router,
    private cdr: ChangeDetectorRef
  ) {}

  search(): void {
    if (!this.query) return;

    this.page = 1;
    this.images = [];
    this.loadPage();
  }

  private loadPage(): void {
    if (this.loading || this.isLoadingMore) return;

    const firstPage = this.page === 1;

    if (firstPage) {
      this.loading = true;
    } else {
      this.isLoadingMore = true;
    }
    this.cdr.detectChanges();

    this.imageService.searchImages(this.query, this.page, 10).subscribe({
      next: (response) => {
        this.images = [...this.images, ...response.images];
        this.totalPages = response.totalPages;

        this.loading = false;
        this.isLoadingMore = false;
        this.cdr.detectChanges();
      },
      error: () => {
        this.loading = false;
        this.isLoadingMore = false;
        this.cdr.detectChanges();
      }
    });
  }

  @HostListener('window:scroll', [])
  onScroll(): void {
    const threshold = 300;
    const position = window.innerHeight + window.scrollY;
    const height = document.body.offsetHeight;

    if (position + threshold >= height) {
      if (this.page < this.totalPages && !this.isLoadingMore && !this.loading) {
        this.page++;
        this.loadPage();
      }
    }
  }

  removeImage(index: number, event: Event): void {
    event.stopPropagation();
    this.images.splice(index, 1);
    this.cdr.detectChanges();
  }

  goToDetail(id: string): void {
    this.router.navigate(['/image', id]);
  }
}
