import { Component } from '@angular/core';
import { AdminService } from '../../services/Admin.service';
import { CommonModule } from '@angular/common';
import { Client } from '../../models/client';
import { Router } from '@angular/router';

@Component({
  selector: 'app-clients',
  imports: [CommonModule],
  templateUrl: './clients.component.html',
  styleUrl: './clients.component.css'
})
export class ClientsComponent {
  clients: Client[] = [];

  constructor(private adminService: AdminService,private router: Router) {}

  ngOnInit() {
    this.loadClients();
  }

  loadClients() {
    this.adminService.getAllClients().subscribe(data => this.clients = data);
  }
   goToDashboard() {
    this.router.navigate(['/admin']);  // Modifie '/dashboard' selon ton chemin de route
  }
}
