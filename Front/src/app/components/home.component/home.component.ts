import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';

@Component({
  standalone: true,
  selector: 'app-home.component',
  imports: [ CommonModule, RouterModule],
  templateUrl: './home.component.html',
})
export class HomeComponent {
  sidebarAbierta = false;

  abrirSidebar() {
    this.sidebarAbierta = true;
  }
  cerrarSidebar() {
    this.sidebarAbierta = false;
  }
}
