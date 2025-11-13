import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Chambre } from '../models/chambre';

@Injectable({
  providedIn: 'root'
})
export class ChambreService {

  private apiUrl = 'http://localhost:8082/api/admins/chambres';  // Change selon ton URL d'API

  constructor(private http: HttpClient) { }

  getAllChambres(): Observable<Chambre[]> {
    return this.http.get<Chambre[]>(this.apiUrl);
  }
  getChambreById(id: number): Observable<Chambre> {
    return this.http.get<Chambre>(`http://localhost:8082/api/chambres/${id}`);
  }
  
  getChambresDisponibles(dateDebut: string, dateFin: string): Observable<Chambre[]> {
    return this.http.get<Chambre[]>(`http://localhost:8082/api/chambres/disponibles?dateDebut=${dateDebut}&dateFin=${dateFin}`);
  }
  
  
}
