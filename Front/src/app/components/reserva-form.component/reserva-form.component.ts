import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, ActivatedRoute, RouterModule} from '@angular/router';

import { ReservaService } from '../../services/reserva.js';
import { HuespedService } from '../../services/huesped.js';
import { EmpresaService } from '../../services/empresa.js';


@Component({
  selector: 'reserva-form',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './reserva-form.component.html',
})
export class ReservaFormComponent {
  /** ---------------- estado ---------------- */
  tipoCliente: 'individuo' | 'empresa' = 'individuo';
  identificador = '';                       // CI o RUT introducidos
  clienteEncontrado = false;
  busquedaFallida = false;
  clienteResumen = '';

  /** Datos de habitación (recibidos por queryParams) */
  habitacionNumero: string | null = null;
  habitacionTipoNombre: string | null = null;

  /** Modelo de reserva */
  reserva = {
    usuarioId: 1,
    huespedId: null as number | null,
    empresaId: null as number | null,
    habitacionId: null as number | null,
    fechaIngreso: '',
    fechaSalida: '',
    telefono: '',
    cantidad: 1,
  };

  constructor(
    private reservaSrv: ReservaService,
    private huespedSrv: HuespedService,
    private empresaSrv: EmpresaService,
    private router: Router,
    private route: ActivatedRoute,
  ) {
    this.route.queryParams.subscribe((p) => {
      this.reserva.habitacionId = p['habitacionId'] ? +p['habitacionId'] : null;
      this.habitacionNumero = p['numHabitacion'] || null;
      this.habitacionTipoNombre = p['tipo'] || null;
    });
  }

  /* ---- buscar CI / RUT según selección ---- */
  buscarCliente() {
    this.busquedaFallida = false;
    this.clienteEncontrado = false;

    if (!this.identificador.trim()) return;

    if (this.tipoCliente === 'individuo') {
      this.huespedSrv.getByCi(this.identificador.trim()).subscribe({
        next: (h) => {
          this.reserva.huespedId = h?.id ?? null;
          this.reserva.empresaId = null;
          this.setResultado(h ? `${h.nombre} ${h.apellido}` : null);
        },
        error: () => this.setResultado(null),
      });
    } else {
      this.empresaSrv.getByRut(this.identificador.trim()).subscribe({
        next: (e) => {
          this.reserva.empresaId = e?.id ?? null;
          this.reserva.huespedId = null;
          this.setResultado(e ? e.nomFantasia : null);
        },
        error: () => this.setResultado(null),
      });
    }
  }

  private setResultado(texto: string | null) {
    if (texto) {
      this.clienteEncontrado = true;
      this.clienteResumen = texto;
    } else {
      this.busquedaFallida = true;
      this.identificador = '';
    }
  }

  /* ---- enviar reserva ---- */
  crearReserva() {
    const body = {
      usuarioId: this.reserva.usuarioId,
      huespedId: this.reserva.huespedId,
      empresaId: this.reserva.empresaId,
      habitacionId: this.reserva.habitacionId,
      fechaIngreso: this.reserva.fechaIngreso,
      fechaSalida: this.reserva.fechaSalida,
      cantidad: this.reserva.cantidad,
      telefono: this.reserva.telefono,
    };

    this.reservaSrv.createReserva(body).subscribe({
      next: () => {
        alert('Reserva creada correctamente');
        this.router.navigate(['/reservas']);
      },
      error: (err) => console.error('Error al crear reserva', err),
    });
  }

  cancelar() {
    this.router.navigate(['/reservas']);
  }
}
