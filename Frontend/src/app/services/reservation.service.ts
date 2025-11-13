import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  private apiUrl = 'http://localhost:8082/reservations'; // Corrigé ici

  constructor(private http: HttpClient) { }

  reserver(clientId: number, chambreId: number, dateDebut: Date, dateFin: Date, typeReservation: string): Observable<any> {
    const params = {
      clientId: clientId.toString(),
      chambreId: chambreId.toString(),
      dateDebut: dateDebut.toISOString(),
      dateFin: dateFin.toISOString(),
      typeReservation: typeReservation
    };

    return this.http.post<any>(`${this.apiUrl}/reserver`, null, { params });
  }
  getReservationsByClient(clientId: number): Observable<any[]> {
    return this.http.get<any[]>(`http://localhost:8082/clients/${clientId}/reservations`);
  }

  getReservationById(id: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/${id}`);
  }
mettreAJourStatut(id: number, statut: string): Observable<any> {
  return this.http.put<any>(`${this.apiUrl}/${id}/statut`, statut, {
    headers: { 'Content-Type': 'text/plain' }
  });
}



}
