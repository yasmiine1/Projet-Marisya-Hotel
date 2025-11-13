import { Component } from '@angular/core';
import { Chambre } from '../../models/chambre';
import { ChambreService } from '../../services/chambre.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-reservation',
  imports: [FormsModule,CommonModule,RouterLink],
  templateUrl: './reservation.component.html',
  styleUrl: './reservation.component.css'
})
export class ReservationComponent {
  
  dateDebut: string = '';
  dateFin: string = '';
  chambresDisponibles: Chambre[] = [];
  message = '';

  constructor(private chambreService: ChambreService, private route: ActivatedRoute) {}
  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.dateDebut = params['dateDebut'] || '';
      this.dateFin = params['dateFin'] || '';
      
      if (this.dateDebut && this.dateFin) {
        this.rechercher();
      }
    });
  }
  
  rechercher() {
    if (!this.dateDebut || !this.dateFin) {
      this.message = 'Veuillez sélectionner les deux dates.';
      return;
    }

    this.chambreService.getChambresDisponibles(this.dateDebut, this.dateFin).subscribe(data => {
      this.chambresDisponibles = data;
      this.message = data.length > 0 ? '' : 'Aucune chambre disponible pour cette période.';
    });
  }
  
}
