import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ReservaService, Reserva } from '../../services/reserva';
import { CommonModule } from '@angular/common';
import { Location } from '@angular/common';

@Component({
  selector: 'reserva-detail',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './reserva-detail.component.html'
})
export class ReservaDetailComponent implements OnInit {
  reserva?: Reserva;

  constructor(
    private route: ActivatedRoute,
    private reservaService: ReservaService,
    private location: Location
  ) {}
  volver() {
    this.location.back();
  }
  ngOnInit() {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.reservaService.getReserva(id).subscribe(data => {
      this.reserva = data;
    });
  }
}
