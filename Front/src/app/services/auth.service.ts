import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

interface UsuarioInfo {
  id: number;
  nombre: string;
  apellido: string;
  nombreUsuario: string;
  rol: string;
}

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/api/auth';

  constructor(private http: HttpClient) {}

  login(data: {
    nombreUsuario: string;
    contrasenia: string;
  }): Observable<UsuarioInfo> {
    return this.http.post<UsuarioInfo>(`${this.apiUrl}/login`, data, {
      withCredentials: true,
    });
  }

  // Método opcional para guardar el usuario en localStorage
  guardarUsuario(usuario: UsuarioInfo) {
    localStorage.setItem('usuarioLogueado', JSON.stringify(usuario));
  }

  obtenerUsuario(): UsuarioInfo | null {
    const data = localStorage.getItem('usuarioLogueado');
    return data ? JSON.parse(data) : null;
  }

  logout() {
    localStorage.removeItem('usuarioLogueado');
    return this.http.post(
      `${this.apiUrl}/logout`,
      {},
      { withCredentials: true }
    );
  }

  register(data: any) {
    return this.http.post('http://localhost:8080/api/auth/register', data, {
      withCredentials: true,
    });
  }

  registerCompleto(data: any) {
    return this.http.post('http://localhost:8080/api/auth/registro', data, {
      withCredentials: true,
    });
  }

  getRoles() {
    return this.http.get<any[]>('http://localhost:8080/api/roles', {
      withCredentials: true,
    });
  }
}
