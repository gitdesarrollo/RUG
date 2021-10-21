import { Component, OnInit, ViewChild } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Subscription } from 'rxjs/Subscription';
import { UsersService } from '../shared/users.service';
import { LoadingService } from '../shared/loading.service';
import { User } from '../shared/user.model';
import { SwalComponent } from '@toverux/ngx-sweetalert2';

declare var Materialize:any;

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit {
  public loading = false;
  passwordForm: FormGroup;
  httpSubscription: Subscription;
  @ViewChild('swalNoCoinciden') private swalNoCoinciden: SwalComponent;
  @ViewChild('swalPasswordEnBlanco') private swalPasswordEnBlanco: SwalComponent;

  constructor(private usersService: UsersService,
    private loadingService: LoadingService) { }

  ngOnInit() {
    this.httpSubscription = this.usersService.usersChanged.subscribe(
      () => {
        this.loading = false;
        this.loadingService.changeLoading(this.loading);
        Materialize.toast('La contraseña fue actualizada exitosamente.', 4000);
      }
    );
    this.initForm();
  }

  private initForm() {
    this.passwordForm = new FormGroup({
      'newPassword': new FormControl('', Validators.required),
      'newPasswordConfirm': new FormControl('', Validators.required),
    });
  }

  onSubmit() {
    // validar password iguales
    let newPassword = this.passwordForm.value.newPassword;
    let newPasswordConfirm = this.passwordForm.value.newPasswordConfirm;
    if (!newPassword) {
      this.swalPasswordEnBlanco.show();
      return;
    }
    if (newPassword != newPasswordConfirm) {
      this.swalNoCoinciden.show();
      return;
    }

    let user: User = this.usersService.authenticatedUser;
    user.password = newPassword;
    this.loading = true;
    this.loadingService.changeLoading(this.loading);
    this.usersService.updateUser(1, user);
  }
}
