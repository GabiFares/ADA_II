import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { AuthService } from '../../services/auth.service';

@Component({
  standalone: true,
  selector: 'app-login',
  templateUrl: './login.component.html',
  imports: [ReactiveFormsModule],
})
export class LoginComponent {
  loginForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {
    this.loginForm = this.fb.group({
      nombreUsuario: [''],
      contrasenia: [''],
    });
  }

  onSubmit() {
    if (this.loginForm.invalid) return;

    const data = this.loginForm.value;

    this.authService.login(data).subscribe({
      next: (usuario) => {
        console.log('✅ Usuario logueado:', usuario);
        this.authService.guardarUsuario(usuario);
        this.router.navigate(['/home'], { state: { usuario } });
      },
      error: () => {
        alert('❌ Nombre de usuario o contraseña incorrectos');
      },
    });
  }
}
