import { Component, OnInit } from '@angular/core';
import { CategoriaService, Categoria } from '../../services/categoria.js';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
@Component({
  selector: 'categoria-list',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './categoria-list.component.html',
  styleUrls: ['./categoria-list.component.css']
})


export class CategoriaListComponent implements OnInit {
  categorias: Categoria[] = [];

  constructor(private categoriaService: CategoriaService) {}

  ngOnInit() {
    this.categoriaService.getCategorias().subscribe(data => {
      this.categorias = data;
    });
  }
}
