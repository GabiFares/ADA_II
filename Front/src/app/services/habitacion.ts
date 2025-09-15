import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Habitacion {
  id: number;
  accesible: boolean;
  disponible: boolean;
  precio: number;
  numHabitacion: number;
  observaciones?: string;
  categoria: {
    id: number;
    nombre: string;
    descripcion: string;
  };
  estado: {
    id: number;
    nombre: string;
    descripcion: string;
  };
  tipo: {
    id: number;
    nombre: string;
    descripcion: string;
  };
}

@Injectable({ providedIn: 'root' })
export class HabitacionService {
  private apiUrl = 'http://localhost:8080/api/habitaciones';

  constructor(private http: HttpClient) {}

  getHabitaciones(): Observable<Habitacion[]> {
    return this.http.get<Habitacion[]>(this.apiUrl, { withCredentials: true });
  }
  getHabitacion(id: number): Observable<Habitacion> {
    return this.http.get<Habitacion>(`${this.apiUrl}/${id}`, {
      withCredentials: true,
    });
  }
}
