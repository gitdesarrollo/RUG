import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { UsersService } from '../shared/users.service';
import { LoadingService } from '../shared/loading.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  public loading = false;
  @ViewChild('f') loginForm: NgForm;
  error: string;

  constructor(private usersService: UsersService,
    private loadingService: LoadingService,
    private router: Router) { }

  ngOnInit() {
  }

  onSubmit() {
    this.loading = true;
    this.loadingService.changeLoading(this.loading);
    this.usersService.login(this.loginForm.value.email, this.loginForm.value.password)
      .subscribe(
        data => {
          this.loading = false;
          this.loadingService.changeLoading(this.loading);
          if (data.code === 1) {
            this.usersService.authenticatedUser = data;
            localStorage.setItem('currentUser', JSON.stringify(data));
            this.router.navigate(['/home']);
          } else {
            // credenciales invalidas
            this.error = "Usuario o Contraseña incorrecto(s)";
          }
        },
        err => {
          this.error = err;
        }
      )
  }
}
