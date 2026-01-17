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
  
    fetch(url)
      .then(res => res.blob())
      .then(blob => {
        const blobUrl = URL.createObjectURL(blob);
        const link = document.createElement('a');
        link.href = blobUrl;
        link.download = filename + '.jpg';
        document.body.appendChild(link);
        link.click();
        link.remove();
        URL.revokeObjectURL(blobUrl);
      });
  }
  

  back(): void {
    this.router.navigate(['/search']);
  }
}
