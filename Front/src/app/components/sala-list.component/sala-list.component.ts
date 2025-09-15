import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { SalaService, Sala } from '../../services/sala.js';

@Component({
  selector: 'sala-list',
  standalone: true,
  imports: [CommonModule, RouterModule, FormsModule],
  templateUrl: './sala-list.component.html',
})
export class SalaListComponent implements OnInit {
  salas: Sala[] = [];
  salasFiltradas: Sala[] = [];

  filtro = {
    capacidad: null as number | null,
    llegada: '',
    salida: '',
    nombre: '',
  };

  constructor(private salaService: SalaService) {}

  ngOnInit(): void {
    this.salaService.getSalas().subscribe((data) => {
      this.salas = data;
      this.filtrarSalas();
    });
  }

  filtrarSalas(): void {
    this.salasFiltradas = this.salas.filter((sala) => {
      const coincideCapacidad =
        this.filtro.capacidad == null ||
        sala.capacidad >= this.filtro.capacidad;
      const coincideNombre =
        !this.filtro.nombre ||
        sala.nombre.toLowerCase().includes(this.filtro.nombre.toLowerCase());
      // Puedes agregar lógica para llegada/salida si lo necesitas
      return coincideCapacidad && coincideNombre;
    });
  }
}
