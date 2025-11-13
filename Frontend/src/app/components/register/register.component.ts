import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-register',
  imports: [FormsModule,RouterLink],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  nom = '';
  prenom = '';
  email = '';
  motDePasse = '';

  constructor(private http: HttpClient, private router: Router) {}

  inscrire() {
    const visiteur = {
      nom: this.nom,
      prenom: this.prenom
    };
    const payload = {
      visiteur,
      email: this.email,
      motDePasse: this.motDePasse
    };

    this.http.post('http://localhost:8082/api/visiteurs/inscription', payload)
      .subscribe({
        next: () => {
          alert('Inscription réussie ! Vous pouvez vous connecter.');
          this.router.navigate(['/login']);
        },
        error: () => alert("Erreur lors de l'inscription")
      });
  }

}
