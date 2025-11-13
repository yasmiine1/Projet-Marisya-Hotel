import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, tap } from 'rxjs';
import { Admin } from '../models/admin';
import { Chambre } from '../models/chambre';
import { Evenement } from '../models/evenement';

@Injectable({
  providedIn: 'root'
})
export class AdminService {
  private apiUrl = 'http://localhost:8082/api/admins';  // Base URL
  private currentAdmin: any = null;

  constructor(private http: HttpClient) {}

  // Connexion
  login(login: string, motDePasse: string): Observable<any> {
    const params = new HttpParams()
      .set('login', login)
      .set('motDePasse', motDePasse);

    return this.http.post<any>(`${this.apiUrl}/login`, null, { params }).pipe(
      tap((admin: any) => {
        this.currentAdmin = admin;
      })
    );
  }

  // Vérifier si connecté
  isLoggedIn(): boolean {
    return this.currentAdmin !== null;
  }

  // Déconnexion
  logout() {
    this.currentAdmin = null;
  }

  // Récupérer l’admin connecté
  getAdmin() {
    return this.currentAdmin;
  }

  // ===== CLIENTS =====
  getAllClients(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/clients`);
  }

  // ===== RÉSERVATIONS =====
  getAllReservations(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/reservations`);
  }

  // ===== CHAMBRES =====
  getAllChambres(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/chambres`);
  }

  createChambre(chambre: Chambre): Observable<any> {
    return this.http.post(`${this.apiUrl}/chambres`, chambre, {
      headers: {
        'Content-Type': 'application/json'
      }
    });
  }
  

  updateChambre(id: number, chambre: any): Observable<any> {
    return this.http.put<any>(`${this.apiUrl}/chambres/${id}`, chambre);
  }

  deleteChambre(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/chambres/${id}`);
  }

  // ===== OFFRES =====
  getAllOffres(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/offres`);
  }

  createOffre(offre: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/offres`, offre, {
      headers: {
        'Content-Type': 'application/json'
      }
    });
  }

  updateOffre(id: number, offre: any): Observable<any> {
    return this.http.put<any>(`${this.apiUrl}/offres/${id}`, offre);
  }

  deleteOffre(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/offres/${id}`);
  }

  // ===== ÉVÉNEMENTS =====
  getAllEvenements(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/evenements`);
  }

   createEvenement(evenement: Evenement): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/evenements`, evenement, {
      headers: {
        'Content-Type': 'application/json'
      }
    });
  }

  updateEvenement(id: number, evenement: any): Observable<any> {
    return this.http.put<any>(`${this.apiUrl}/evenements/${id}`, evenement);
  }

  deleteEvenement(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/evenements/${id}`);
  }
}
  

