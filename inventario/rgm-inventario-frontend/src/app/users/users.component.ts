import { Component, OnInit, EventEmitter } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { User } from '../shared/user.model';
import { MaterializeAction } from 'angular2-materialize';
import { Subscription } from 'rxjs/Subscription';
import { UsersService } from '../shared/users.service';
import { LoadingService } from '../shared/loading.service';
import { environment } from '../../environments/environment';

declare var Materialize:any;

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {
  public loading = false;
  userForm: FormGroup;
  users: User[];
  modalActions = new EventEmitter<string|MaterializeAction>();
  modalUser: User;
  httpSubscription: Subscription;
  localSubscription: Subscription;
  editMode = false;
  deleteMode = false;
  editIndex: number;
  editId: number;
  urlReporteUsuarios: string;

  constructor(private usersService: UsersService,
    private loadingService: LoadingService) { }

  ngOnInit() {
    this.urlReporteUsuarios = environment.base_url + '/reporte-usuario?usuario=' + this.usersService.authenticatedUser.usuarioId;
    this.usersService.users = [];
    this.loading = true;
    this.loadingService.changeLoading(this.loading);
    this.httpSubscription = this.usersService.fetchData().subscribe(
      data => {
        this.users = data.value;
        this.usersService.addUsers(this.users);
      },
      err => console.error(err),
      () => {
        this.loading = false;
        this.loadingService.changeLoading(this.loading);
      }
    );

    this.localSubscription = this.usersService.usersChanged.subscribe(
      (users: User[]) => {
        this.loading = false;
        this.loadingService.changeLoading(this.loading);
        let message = '';
        if (this.deleteMode) {
          message = 'El usuario fue eliminado exitosamente.';
        } else if (this.editMode) {
          message = 'El usuario fue modificado exitosamente.';
        } else {
          message = 'El usuario fue creado exitosamente.';
        }
        this.users = users;
        Materialize.toast(message, 4000);
        this.onCancelClicked();
      }
    );

    this.initForm();
  }

  private initForm() {
    this.userForm = new FormGroup({
      'nombre': new FormControl('', Validators.required),
      'email': new FormControl('', [Validators.required, Validators.email]),
      'rol': new FormControl('', Validators.required)
    });
  }

  onAddUserClicked() {
    this.modalUser = new User;
    this.editMode = false;
    this.modalActions.emit({action:"modal", params:["open"]});
  }

  onEditUserClicked(user: User, index: number) {
    this.modalUser = user;
    this.editMode = true;
    this.editIndex = index;
    this.editId = user.usuarioId;
    this.userForm.patchValue({
      nombre: user.nombre,
      email: user.email,
      rol: user.rol
    });
    Materialize.updateTextFields();
    this.modalActions.emit({action:"modal", params:["open"]});
  }

  onRemoveUserClicked(index: number) {
    let user: User = new User;
    user.usuarioId = this.users[index].usuarioId;
    user.estado = 'I';
    this.loading = true;
    this.loadingService.changeLoading(this.loading);
    this.usersService.updateUser(index, user);
  }

  onSubmit() {
    let user: User = new User;
    user.nombre = this.userForm.value.nombre;
    user.email = this.userForm.value.email;
    user.rol = this.userForm.value.rol;
    this.loading = true;
    this.loadingService.changeLoading(this.loading);
    if (this.editMode) {
      user.usuarioId = this.editId;
      this.usersService.updateUser(this.editIndex, user);
    } else {
      this.usersService.addUser(user);
    }
  }

  onCancelClicked() {
    this.modalActions.emit({action:"modal",params:['close']});
    this.userForm.reset();
    this.editMode = false;
    this.deleteMode = false;
    this.editIndex = -1;
  }
}
