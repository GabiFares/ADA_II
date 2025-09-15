import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { SalaService, Sala } from '../../services/sala.js';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'sala-detail',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './sala-detail.component.html',
})
export class SalaDetailComponent implements OnInit {
  sala: Sala | null = null;

  constructor(
    private route: ActivatedRoute,
    private salaService: SalaService
  ) {}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.salaService.getSala(id).subscribe(data => {
      this.sala = data;
    });
  }
}
