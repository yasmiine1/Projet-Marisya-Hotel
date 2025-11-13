import { Component } from '@angular/core';
import { AdminService } from '../../services/Admin.service';
import { CommonModule } from '@angular/common';
import { Evenement } from '../../models/evenement';
import { Offre } from '../../models/offre';

@Component({
  selector: 'app-home',
  imports: [CommonModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  images: string[] = [
    'img3.jpg',
    'img6.jpg',
    'img4.jpg',
    'reception.jpg',
    'img1.jpg'
  ];
  currentSlideIndex = 0;
  evenements: Evenement[] = [];
  offres: Offre[] = [];

  constructor(private adminService: AdminService) {}
  ngOnInit(): void {
    this.showSlide(this.currentSlideIndex);
    this.getEvenements();
    this.getOffres();
  }
    

  

 showSlide(index: number): void {
    const slides = document.querySelectorAll('.slide');
    const slider = document.querySelector('.slider') as HTMLElement;
  
    if (slider) {
      slider.style.transform = `translateX(-${index * 100}%)`;
    }
  
    this.currentSlideIndex = index;
  }
  
  nextSlide(): void {
    const nextIndex = (this.currentSlideIndex + 1) % this.images.length;
    this.showSlide(nextIndex);
  }

  prevSlide(): void {
    const prevIndex = (this.currentSlideIndex - 1 + this.images.length) % this.images.length;
    this.showSlide(prevIndex);
  }
  getEvenements() {
    this.adminService.getAllEvenements().subscribe(data => {
      this.evenements = data;
    });
  }

  getOffres() {
    this.adminService.getAllOffres().subscribe(data => {
      this.offres = data;
    });
  }
}
