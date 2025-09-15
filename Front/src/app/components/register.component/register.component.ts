import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  standalone: true,
  selector: 'app-register',
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent implements OnInit {
  registerForm: FormGroup;
  error: string | null = null;
  success: string | null = null;
  roles: any[] = [];

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {
    this.registerForm = this.fb.group({
      nombre: ['', Validators.required],
      apellido: ['', Validators.required],
      nombreUsuario: ['', Validators.required],
      contrasenia: ['', [Validators.required, Validators.minLength(8)]],
      repetirContrasenia: ['', Validators.required],
      ci: ['', [Validators.required, Validators.pattern('^[0-9]{7,8}$')]],
      telefono: [
        '',
        [Validators.required, Validators.pattern('^[0-9]{7,15}$')],
      ],
      email: ['', [Validators.required, Validators.email]],
      pais: ['', Validators.required],
      departamento: ['', Validators.required],
      ciudad: ['', Validators.required],
      calle: ['', Validators.required],
      numPuerta: ['', Validators.required],
      idRol: [null, Validators.required],
    });
  }

  ngOnInit() {
    this.authService.getRoles().subscribe({
      next: (roles) => (this.roles = roles),
      error: () => (this.roles = []),
    });
  }

  onSubmit() {
    this.error = null;
    this.success = null;
    if (this.registerForm.invalid) {
      this.error = 'Por favor, completa todos los campos correctamente.';
      return;
    }
    if (
      this.registerForm.value.contrasenia !==
      this.registerForm.value.repetirContrasenia
    ) {
      this.error = 'Las contraseñas no coinciden';
      return;
    }
    const data = { ...this.registerForm.value };
    delete data.repetirContrasenia;
    this.authService.registerCompleto(data).subscribe({
      next: () => {
        this.success = 'Usuario registrado correctamente';
        setTimeout(() => {
          this.router.navigate(['/home']);
        }, 2000);
        this.registerForm.reset();
      },
      error: (err) => {
        // Mejor manejo de errores según el backend
        if (err.status === 409) {
          this.error = 'El usuario o email ya existe.';
        } else if (err.status === 400) {
          this.error = err.error?.message || 'Datos inválidos.';
        } else {
          this.error = 'Error inesperado al registrar usuario.';
        }
      },
    });
  }
}
