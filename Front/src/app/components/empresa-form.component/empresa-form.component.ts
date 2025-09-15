import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, ActivatedRoute, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

import { EmpresaService } from '../../services/empresa';
import { Empresa} from '../../models/empresa.model';

@Component({
  selector: 'app-empresa-form',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './empresa-form.component.html',
})
export class EmpresaFormComponent {
  empresa: Empresa = {
    rut: '',
    nomFantasia: '',
    razonSocial: '',
    personaId: 1,
  };

  constructor(
    private empresaSrv: EmpresaService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  crear(): void {
    this.empresaSrv.create(this.empresa).subscribe({
      next: (empresaCreada: Empresa) => {
        const { habitacionId, tipo, numHabitacion } = this.route.snapshot.queryParams;

        this.router.navigate(['/reservas/nueva'], {
          queryParams: {
            habitacionId,
            tipo,
            numHabitacion,
            ci: null,
            rut: empresaCreada.rut,
          },
        });
      },
      error: (err) => {
        console.error('Error al crear empresa:', err);
      },
    });
  }

  cancelar(): void {
    this.router.navigate(['/reservas']);
  }
}
