import { Component, OnInit } from '@angular/core';
import { Chambre } from '../../models/chambre';
import { ChambreService } from '../../services/chambre.service';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-chambre',
  imports: [CommonModule,RouterLink],
  templateUrl: './chambre.component.html',
  styleUrl: './chambre.component.css'
})
export class ChambreComponent implements OnInit {
  chambres: Chambre[] = [];

  constructor(private chambreService: ChambreService) { }

  ngOnInit(): void {
    this.chambreService.getAllChambres().subscribe(data => {
      this.chambres = data;
    });
  }
}
