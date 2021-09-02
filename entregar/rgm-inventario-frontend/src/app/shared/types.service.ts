import { Injectable } from "@angular/core";
import { Subject } from "rxjs/Subject";
import { HttpClient } from "@angular/common/http";
import { Type } from "./type.model";
import { ResponseRs } from "./response.model";
import { environment } from "../../environments/environment";

@Injectable()
export class TypesService {
  types: Type[] = [];
  typesChanged = new Subject<Type[]>();

  constructor(private http: HttpClient) {}

  getTypes() {
    return this.types.slice();
  }

  addType(type: Type) {
    this.saveData(type).subscribe(
      (response) => {
        let savedType: Type = response.value;
        this.types.push(savedType);
        this.typesChanged.next(this.getTypes());
      },
      err => console.error(err)
    );
  }

  addTypes(types: Type[]) {
    this.types.push(...types);
    // this.typesChanged.next(this.getTypes());
  }

  updateType(index: number, newType: Type) {
    this.updateData(newType).subscribe(
      (response) => {
        let updatedType: Type = response.value;
        this.types[index] = updatedType;
        this.typesChanged.next(this.getTypes());
      }
    );
  }

  deleteType(index: number, typeId: number) {
    this.deleteData(typeId).subscribe(
      (response) => {
        let updatedType: Type = response.value;
        this.types.splice(index, 1);
        this.typesChanged.next(this.getTypes());
      }
    );
  }

  fetchData() {
    return this.http.get<ResponseRs>(environment.api_url + '/tipos-articulos');
  }

  saveData(type: Type) {
    return this.http.post<ResponseRs>(environment.api_url + '/tipos-articulos', type);
  }

  updateData(type: Type) {
    return this.http.put<ResponseRs>(environment.api_url + '/tipos-articulos/' + type.id, type);
  }

  deleteData(typeId: number) {
    return this.http.delete<ResponseRs>(environment.api_url + '/tipos-articulos/' + typeId);
  }
}
