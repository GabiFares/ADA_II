import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { HabitacionService } from '../../services/habitacion.js';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { Location } from '@angular/common';

@Component({
  selector: 'habitacion-detail',
  standalone: true,
  templateUrl: './habitacion-detail.component.html',
  imports: [CommonModule, RouterModule]
})
export class HabitacionDetailComponent implements OnInit {
  habitacion: any;

  constructor(
    private route: ActivatedRoute,
    private habitacionService: HabitacionService,
    private location: Location
  ) {}

  Volver(){
    this.location.back()
  }
  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    this.habitacionService.getHabitacion(Number(id)).subscribe(data => {
      this.habitacion = data;
    });
  }
}
