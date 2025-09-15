import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, ActivatedRoute, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

import { HuespedService } from '../../services/huesped';
import { Huesped } from '../../models/huesped.model';

@Component({
  selector: 'app-huesped-form',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './huesped-form.component.html',
})
export class HuespedFormComponent {
  huesped: Huesped = {
    ci: '',
    nombre: '',
    apellido: '',
    personaId: 1,
  };

  constructor(
    private huespedSrv: HuespedService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  crear(): void {
    this.huespedSrv.create(this.huesped).subscribe({
      next: (huespedCreado: Huesped) => {
        const { habitacionId, tipo, numHabitacion } = this.route.snapshot.queryParams;

        this.router.navigate(['/reservas/nueva'], {
          queryParams: {
            habitacionId,
            tipo,
            numHabitacion,
            ci: huespedCreado.ci,
          },
        });
      },
      error: (err) => {
        console.error('Error al crear huésped:', err);
      },
    });
  }

  cancelar(): void {
    this.router.navigate(['/huespedes']);
  }
}
