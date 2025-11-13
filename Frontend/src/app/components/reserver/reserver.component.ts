import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ChambreService } from '../../services/chambre.service';
import { AuthService } from '../../services/auth.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ReservationService } from '../../services/reservation.service';

@Component({
  selector: 'app-reserver',
  imports: [FormsModule,CommonModule],
  templateUrl: './reserver.component.html',
  styleUrl: './reserver.component.css'
})
export class ReserverComponent {
 constructor(
    private route: ActivatedRoute,
    private router: Router,
    private chambreService: ChambreService,
    private authService: AuthService,
    private reservationService: ReservationService
  ) {}

  chambre: any;
  dateDebut: string = '';
  dateFin: string = '';
  options = {
    allInclusive: false,
    petitDej: false,
    demiPension: false,
    litBebe: false
  };
  totalPrix: number = 0;
  alertVisible: boolean = false;
  isPaye: boolean = false;

  reservation: any = {
    statut: 'Réservé',
    typeReservation: '',
    client: {},
    paiement: {},
    dateReservations: []
  };

  ngOnInit(): void {
    const id = this.route.snapshot.params['numero'];
    this.dateDebut = this.route.snapshot.queryParams['dateDebut'];
    this.dateFin = this.route.snapshot.queryParams['dateFin'];

    if (id) {
      this.chambreService.getChambreById(id).subscribe({
        next: data => {
          this.chambre = data;
          this.updatePrix();
        },
        error: err => console.error('Erreur chambre', err)
      });
    } else {
      console.error("Aucun ID de chambre reçu");
    }
  }

  updatePrix() {
    this.totalPrix = this.chambre.prix;

    if (this.options.allInclusive) this.totalPrix += 50;
    if (this.options.petitDej) this.totalPrix += 10;
    if (this.options.demiPension) this.totalPrix += 30;
    if (this.options.litBebe) this.totalPrix += 20;
  }

  onOptionChange(option: string) {
    if (option === 'allInclusive' && this.options.allInclusive) {
      this.options.petitDej = false;
      this.options.demiPension = false;
    } else if ((option === 'petitDej') && (this.options.petitDej)) {
      this.options.allInclusive = false;
      this.options.demiPension = false;
    } else if ((option === 'demiPension') && (this.options.demiPension)) {
      this.options.allInclusive = false;
      this.options.petitDej = false;
    }

    this.updatePrix();
  }

  submitReservation() {
    if (!this.authService.isLoggedIn()) {
      this.router.navigate(['/login']);
      return;
    }

    const client = this.authService.getClient();
    if (!client) {
      console.error("Aucun client connecté.");
      return;
    }

    const chambreId = this.chambre.numero;
    const typeReservation = this.options.allInclusive ? 'allInclusive' :
                            this.options.demiPension ? 'demiPension' :
                            this.options.petitDej ? 'petitDejeuner' :
                            'standard';

    this.reservationService.reserver(
      client.id,
      chambreId,
      new Date(this.dateDebut),
      new Date(this.dateFin),
      typeReservation
    ).subscribe({
      next: (res) => {
        console.log("Réservation reçue :", res);
        this.reservation = res;
        // Appeler getReservationById pour récupérer la réservation complète
        this.reservationService.getReservationById(res.id).subscribe({
          next: (data) => {
            this.reservation = data;  // Mettre à jour avec les détails
            this.alertVisible = true;
            setTimeout(() => this.router.navigate(['/chambres']), 4000);
          },
          error: (err) => {
            console.error("Erreur lors de la récupération de la réservation :", err);
            alert("Erreur lors de la récupération de la réservation.");
          }
        });
      },
      error: (err) => {
        console.error("Erreur de réservation :", err);
        alert("La réservation a échoué.");
      }
    });
  }

  payerReservation() {
    if (!this.reservation || !this.reservation.id) {
      console.error("ID de réservation introuvable.", this.reservation);
      alert("Erreur : réservation introuvable.");
      return;
    }

    this.isPaye = true;

    this.reservationService.mettreAJourStatut(this.reservation.id, 'payé')
      .subscribe({
        next: res => {
          console.log('Statut mis à jour', res);
          this.reservation.statut = 'payé';
        },
        error: err => {
          console.error('Erreur lors de la mise à jour', err);
          alert("La mise à jour du statut a échoué.");
        }
      });
  }
}