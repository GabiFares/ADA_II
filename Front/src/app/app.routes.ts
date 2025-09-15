import { Routes } from '@angular/router';
import { LoginComponent } from './components/login.component/login.component';
import { CategoriaListComponent } from './components/categoria-list/categoria-list.component';
import { CategoriaDetailComponent } from './components/categoria-detail.component/categoria-detail.component';
import { CategoriaFormComponent } from './components/categoria-form.component/categoria-form.component';
import { ReservaListComponent } from './components/reserva-list.component/reserva-list.component';
import { ReservaDetailComponent } from './components/reserva-detail.component/reserva-detail.component';
import { ReservaFormComponent } from './components/reserva-form.component/reserva-form.component';
import { HabitacionListComponent } from './components/habitacion-list.component/habitacion-list.component';
import { HabitacionDetailComponent } from './components/habitacion-detail.component/habitacion-detail.component';
import { SalaDetailComponent } from './components/sala-detail.component/sala-detail.component';
import { SalaListComponent } from './components/sala-list.component/sala-list.component';
import { HomeComponent } from './components/home.component/home.component';
import { HuespedListComponent } from './components/huesped-list.component/huesped-list.component';
import { HuespedFormComponent } from './components/huesped-form.component/huesped-form.component';
import { HuespedDetailComponent } from './components/huesped-detail.component/huesped-detail.component';
import { EmpresaListComponent } from './components/empresa-list.component/empresa-list.component';
import { EmpresaFormComponent } from './components/empresa-form.component/empresa-form.component';
import { EmpresaDetailComponent } from './components/empresa-detail.component/empresa-detail.component';
import { LayoutComponent } from './components/layout.component/layout.component';
import { RegisterComponent } from './components/register.component/register.component';
import { AdminGuard } from './services/admin.guard';


import { Component } from '@angular/core';

export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent, canActivate: [AdminGuard] },

  {
    path: '',
    component: LayoutComponent, // todas estas rutas usan el layout con hamburguesa
    children: [
      { path: '', redirectTo: 'home', pathMatch: 'full' },
      { path: 'home', component: HomeComponent },
      { path: 'categorias', component: CategoriaListComponent },
      { path: 'categorias/nueva', component: CategoriaFormComponent },
      { path: 'categorias/:id', component: CategoriaDetailComponent },
      { path: 'reservas', component: ReservaListComponent },
      { path: 'reservas/nueva', component: ReservaFormComponent },
      { path: 'reservas/:id', component: ReservaDetailComponent },
      { path: 'habitaciones', component: HabitacionListComponent },
      { path: 'habitaciones/:id', component: HabitacionDetailComponent },
      { path: 'salas', component: SalaListComponent },
      { path: 'salas/:id', component: SalaDetailComponent },
      { path: 'huespedes', component: HuespedListComponent },
      { path: 'huespedes/nuevo', component: HuespedFormComponent },
      { path: 'huespedes/:id', component: HuespedDetailComponent },
      { path: 'empresas', component: EmpresaListComponent },
      { path: 'empresas/nueva', component: EmpresaFormComponent },
      { path: 'empresas/:id', component: EmpresaDetailComponent },
    ],
  },
];
