import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  standalone: true,
  selector: 'app-layout',
  imports: [CommonModule, RouterModule],
  templateUrl: './layout.component.html',
})
export class LayoutComponent {
  sidebarOpen = false;

  constructor(private authService: AuthService) {}

  toggleSidebar() {
    this.sidebarOpen = !this.sidebarOpen;
  }

  cerrarSesion() {
    // Aquí puedes agregar la lógica para cerrar sesión
    // Por ejemplo, limpiar el localStorage y redirigir al login
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    window.location.href = '/login';
  }

  esAdmin(): boolean {
    const usuario = this.authService.obtenerUsuario();
    return !!usuario && usuario.rol === 'Admin';
  }
}
