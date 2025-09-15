import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CategoriaService, Categoria } from '../../services/categoria';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'categoria-detail',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './categoria-detail.component.html',
  styleUrls: ['./categoria-detail.component.css']
})
export class CategoriaDetailComponent implements OnInit {
  categoria?: Categoria;

  constructor(
    private route: ActivatedRoute,
    private categoriaService: CategoriaService
  ) {}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.categoriaService.getCategoria(id).subscribe((data) => {
      this.categoria = data;
    });
  }
}
