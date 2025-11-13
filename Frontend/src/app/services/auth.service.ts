import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiUrl = 'http://localhost:8082/clients'; // adapte au besoin
  private currentClient: any = null;

  constructor(private http: HttpClient, private router: Router) {}

  login(email: string, motDePasse: string): Observable<any> {
    return this.http.post(`${this.apiUrl}/login?email=${email}&motDePasse=${motDePasse}`, {}, { withCredentials: true })
    .pipe(tap((client: any) => this.currentClient = client));
  
  }
  restoreSession(): Observable<any> {
    return this.http.get(`${this.apiUrl}/me`, { withCredentials: true })
      .pipe(tap(client => this.currentClient = client));
  }
  

  logout() {
    this.http.post(`${this.apiUrl}/logout`, {}, { withCredentials: true, responseType: 'text' })
      .subscribe({
        next: (response) => {
          console.log(response); // Affiche la réponse texte, ici "Déconnecté"
          this.currentClient = null;  // Met à jour l'état utilisateur
          this.router.navigate(['/home']);  // Redirige vers la page d'accueil
        },
        error: (err) => {
          console.error('Erreur lors de la déconnexion', err);
        }
      });
  }
  

  isLoggedIn(): boolean {
    return this.currentClient != null;
  }

  getClient() {
    return this.currentClient;
  }
  updateClient(client: any): Observable<any> {
    return this.http.put(`${this.apiUrl}/${client.id}`, client, { withCredentials: true });
  }
  
}

