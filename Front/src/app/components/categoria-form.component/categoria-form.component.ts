import { Component } from '@angular/core';
import { CategoriaService, Categoria } from '../../services/categoria';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'categoria-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './categoria-form.component.html'
})
export class CategoriaFormComponent {
  nuevaCategoria: Categoria = {
    id: 0,
    nombre: '',
    descripcion: ''
  };

  constructor(private categoriaService: CategoriaService) {}

  crearCategoria() {
    this.categoriaService.createCategoria(this.nuevaCategoria).subscribe({
      next: () => {
        alert('Categoría creada exitosamente');
        this.nuevaCategoria = { id: 0, nombre: '', descripcion: '' };
      },
      error: err => console.error('Error al crear categoría', err)
    });
  }
}
