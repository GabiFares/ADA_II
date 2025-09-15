import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Huesped } from '../models/huesped.model';


@Injectable({ providedIn: 'root' })
export class HuespedService {
  private api = 'http://localhost:8080/api/huespedes';

  constructor(private http: HttpClient) {}

  /** GET /api/huespedes */
  getAll(): Observable<Huesped[]> {
    return this.http.get<Huesped[]>(this.api);
  }

  /** GET /api/huespedes/{id} */
  getById(id: number): Observable<Huesped> {
    return this.http.get<Huesped>(`${this.api}/${id}`);
  }

  /** GET /api/huespedes/ci/{ci}  (ajusta si tu endpoint es distinto) */
  getByCi(ci: string): Observable<Huesped> {
    return this.http.get<Huesped>(`${this.api}/ci/${ci}`);
  }

  /** POST /api/huespedes */
  create(h: Huesped): Observable<Huesped> {
    return this.http.post<Huesped>(this.api, h);
  }
}
