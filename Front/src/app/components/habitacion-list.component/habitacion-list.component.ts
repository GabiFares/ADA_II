import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { HabitacionService, Habitacion } from '../../services/habitacion.js';
import { Router } from '@angular/router';
import { Location } from '@angular/common';

@Component({
  selector: 'habitacion-list',
  standalone: true,
  imports: [CommonModule, RouterModule], // 👈 AGREGADO RouterModule
  templateUrl: './habitacion-list.component.html',
})
export class HabitacionListComponent implements OnInit {
  habitaciones: Habitacion[] = [];

  constructor(private habitacionService: HabitacionService, private location: Location) {}

  volver(){
    this.location.back()
  }
  ngOnInit(): void {
    this.habitacionService.getHabitaciones().subscribe({
      next: (data) => (this.habitaciones = data),
      error: (err) => console.error('Error cargando habitaciones', err),
    });
  }
}
