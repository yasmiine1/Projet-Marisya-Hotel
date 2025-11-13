import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ReservationService } from '../../services/reservation.service';

@Component({
  selector: 'app-mon-compte',
  imports: [FormsModule,CommonModule],
  templateUrl: './mon-compte.component.html',
  styleUrl: './mon-compte.component.css'
})
export class MonCompteComponent {
  client: any;
  reservations: any[] = [];  // Liste des réservations

  constructor(
    private authService: AuthService, 
    private router: Router, 
    private reservationService: ReservationService  // Injection du service ReservationService
  ) {}

  ngOnInit(): void {
    // Récupérer les informations du client
    if (this.authService.isLoggedIn()) {
      this.client = this.authService.getClient();
      this.loadReservations();  // Charger les réservations du client
    } else {
      this.router.navigate(['/login']); // Rediriger si non connecté
    }
  }

  // Charger les réservations du client
  loadReservations(): void {
    if (this.client && this.client.id) {
      this.reservationService.getReservationsByClient(this.client.id).subscribe({
        next: (reservations) => {
          this.reservations = reservations;
        },
        error: (err: any) => {
          console.error('Erreur lors de la récupération des réservations', err);
          alert('Une erreur est survenue lors de la récupération des réservations.');
        }
      });
    }
  }

  // Mettre à jour le compte client
  updateClient(): void {
    this.authService.updateClient(this.client).subscribe({
      next: (updatedClient) => {
        this.client = updatedClient;
        alert('Compte mis à jour avec succès!');
      },
      error: (err) => {
        console.error('Erreur lors de la mise à jour du compte', err);
        alert('Une erreur est survenue lors de la mise à jour.');
      }
    });
  }

  // Payer une réservation
  payerReservation(reservationId: number): void {
    this.reservationService.mettreAJourStatut(reservationId, 'payé').subscribe({
      next: (res) => {
        console.log('Réservation payée:', res);
        this.loadReservations();  // Recharger les réservations après paiement
      },
      error: (err) => {
        console.error('Erreur lors du paiement', err);
        alert('Erreur lors du paiement.');
      }
    });
  }

  // Annuler une réservation
  annulerReservation(reservationId: number): void {
    this.reservationService.mettreAJourStatut(reservationId, 'annulée').subscribe({
      next: (res: any) => {
        console.log('Réservation annulée:', res);
        this.loadReservations();  // Recharger les réservations après annulation
      },
      error: (err: any) => {
        console.error('Erreur lors de l\'annulation', err);
        alert('Erreur lors de l\'annulation.');
      }
    });
  }

  // Se déconnecter
  logout(): void {
    this.authService.logout();
    this.router.navigate(['/login']); // Rediriger vers la page de login
  }
}