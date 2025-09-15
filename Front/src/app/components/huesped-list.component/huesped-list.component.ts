import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { HuespedService } from '../../services/huesped.js';
import { Huesped } from '../../models/huesped.model.js';
import { Location } from '@angular/common';

@Component({
  selector: 'huesped-list',
  standalone: true,
  templateUrl: './huesped-list.component.html',
  imports: [CommonModule, RouterModule],
})
export class HuespedListComponent implements OnInit {
  huespedes: Huesped[] = [];

  constructor(private srv: HuespedService, private location: Location) {}

  Volver(){
    this.location.back()
  }
  ngOnInit() {
    this.srv.getAll().subscribe((hs) => (this.huespedes = hs));
  }
}
