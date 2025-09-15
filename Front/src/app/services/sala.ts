import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Sala {
  id: number;
  nombre: string;
  capacidad: number;
  precio: number;
  equipamiento: string;
  estado: {
    id: number;
    nombre: string;
    descripcion: string;
  };
}

@Injectable({
  providedIn: 'root',
})
export class SalaService {
  private apiUrl = 'http://localhost:8080/api/salas';

  constructor(private http: HttpClient) {}

  getSalas(): Observable<Sala[]> {
    return this.http.get<Sala[]>(this.apiUrl, { withCredentials: true });
  }

  getSala(id: number): Observable<Sala> {
    return this.http.get<Sala>(`${this.apiUrl}/${id}`, {
      withCredentials: true,
    });
  }
}
