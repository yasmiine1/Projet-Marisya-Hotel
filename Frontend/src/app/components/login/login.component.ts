import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { Router, RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  imports: [FormsModule,RouterLink],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  email = '';
  motDePasse = '';

  constructor(private authService: AuthService, private router: Router) {}

  onLogin() {
    this.authService.login(this.email, this.motDePasse).subscribe({
      next: () => {
        // Vérifie s'il y a une redirection en attente
        const redirect = localStorage.getItem('redirectAfterLogin');
        if (redirect) {
          const redirectData = JSON.parse(redirect);
          localStorage.removeItem('redirectAfterLogin'); // Nettoyage
          this.router.navigate([redirectData.route], { queryParams: redirectData.queryParams });
        } else {
          this.router.navigate(['/reserver']); // Redirection par défaut
        }
      },
      error: () => alert('Email ou mot de passe incorrect')
    });
  }
  
  

}
