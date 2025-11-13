import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AdminService } from '../../services/Admin.service';

@Component({
  selector: 'app-login-admin',
  imports: [CommonModule,FormsModule],
  templateUrl: './login-admin.component.html',
  styleUrl: './login-admin.component.css'
})
export class LoginAdminComponent {
  login: string = '';
  motDePasse: string = '';
  erreur: string = '';

  constructor(private adminService: AdminService, private router: Router) {}  // Injection de AdminService ici

  // Méthode de login pour l'admin
  loginAdmin() {
    // Vérification des champs vides
    if (!this.login || !this.motDePasse) {
      this.erreur = 'Le login et le mot de passe sont requis';
      return;
    }

    // Appel au service de connexion
    this.adminService.login(this.login, this.motDePasse).subscribe({
      next: (admin: any) => {
        console.log('Admin connecté', admin);
        this.router.navigate(['/admin']);  // Rediriger vers le dashboard de l'admin
      },
      error: (err: any) => {
        console.error('Erreur lors de la connexion', err);
        if (err.status === 401) {
          this.erreur = 'Identifiants incorrects. Veuillez réessayer.';
        } else {
          this.erreur = 'Une erreur s\'est produite. Veuillez réessayer plus tard.';
        }
      }
    });
  }
}