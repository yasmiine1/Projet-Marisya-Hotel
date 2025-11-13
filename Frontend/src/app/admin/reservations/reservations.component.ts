import { Component } from '@angular/core';
import { AdminService } from '../../services/Admin.service';
import { CommonModule } from '@angular/common';
import { Reservation } from '../../models/reservation';
import { Router } from '@angular/router';

@Component({
  selector: 'app-reservations',
  imports: [CommonModule],
  templateUrl: './reservations.component.html',
  styleUrl: './reservations.component.css'
})
export class ReservationsComponent {
 reservations: Reservation[] = [];

  constructor(private adminService: AdminService,private router: Router) {}

  ngOnInit(): void {
    this.loadReservations();
  }

  loadReservations(): void {
    this.adminService.getAllReservations().subscribe(data => {
      this.reservations = data;
    });
  }
  getStatutText(statut: any): string {
  try {
    const parsed = JSON.parse(statut);
    return parsed.statut || statut;
  } catch (e) {
    return statut; // si ce n'est pas du JSON
  }
}
 goToDashboard() {
    this.router.navigate(['/admin']);  // Modifie '/dashboard' selon ton chemin de route
  }
}
