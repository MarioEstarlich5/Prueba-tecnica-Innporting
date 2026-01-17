import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ImageService } from '../../services/image';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-detail',
  standalone: true,
  imports: [CommonModule, MatButtonModule],
  templateUrl: './detail.html',
  styleUrl: './detail.scss'
})
export class DetailComponent implements OnInit {

  image: any;

  constructor(
    private route: ActivatedRoute,
    private imageService: ImageService,
    private router: Router,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');

    this.imageService.getImageDetail(id!).subscribe(res => {
      this.image = res;
      this.cdr.detectChanges();
    });
  }

  download(): void {
    const url = this.image.downloadUrl;
    const filename = this.image.title || 'imagen';
  
    const newTab = window.open(url, '_blank');
    if (!newTab) return;
  
    newTab.onload = () => {
      const link = newTab.document.createElement('a');
      link.href = url;
      link.download = filename;
      newTab.document.body.appendChild(link);
      link.click();
    };
  }

  back(): void {
    this.router.navigate(['/search']);
  }
}
