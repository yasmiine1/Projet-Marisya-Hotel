import { Component, OnInit } from '@angular/core';
import { Chambre } from '../../models/chambre';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { ChambreService } from '../../services/chambre.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-detail-chambre',
  imports: [CommonModule,RouterLink],
  templateUrl: './detail-chambre.component.html',
  styleUrl: './detail-chambre.component.css'
})
export class DetailChambreComponent implements OnInit{
  chambre!: Chambre;
  currentImageIndex = 0;

  constructor(
    private route: ActivatedRoute,
    private chambreService: ChambreService
  ) {}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.chambreService.getChambreById(id).subscribe(data => {
      this.chambre = data;
    });
  }

  prevSlide() {
    if (this.chambre && this.chambre.images?.length) {
      this.currentImageIndex =
        (this.currentImageIndex - 1 + this.chambre.images.length) %
        this.chambre.images.length;
    }
  }

  nextSlide() {
    if (this.chambre && this.chambre.images?.length) {
      this.currentImageIndex =
        (this.currentImageIndex + 1) % this.chambre.images.length;
    }
  }

}
