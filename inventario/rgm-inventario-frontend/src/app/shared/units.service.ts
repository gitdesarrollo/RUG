import { Injectable } from "@angular/core";
import { Subject } from "rxjs/Subject";
import { HttpClient } from "@angular/common/http";
import { Unit } from "./unit.model";
import { ResponseRs } from "./response.model";
import { environment } from "../../environments/environment";

@Injectable()
export class UnitsService {
  units: Unit[] = [];
  unitsChanged = new Subject<Unit[]>();

  constructor(private http: HttpClient) {}

  getUnits() {
    return this.units.slice();
  }

  addUnit(unit: Unit) {
    this.saveData(unit).subscribe(
      (response) => {
        let savedUnit: Unit = response.value;
        this.units.push(savedUnit);
        this.unitsChanged.next(this.getUnits());
      },
      err => console.error(err)
    );
  }

  addUnits(units: Unit[]) {
    this.units.push(...units);
    // this.unitsChanged.next(this.getUnits());
  }

  updateUnit(index: number, newUnit: Unit) {
    this.updateData(newUnit).subscribe(
      (response) => {
        let updatedUnit: Unit = response.value;
        this.units[index] = updatedUnit;
        this.unitsChanged.next(this.getUnits());
      }
    );
  }

  deleteUnit(index: number, unitId: number) {
    this.deleteData(unitId).subscribe(
      (response) => {
        let updatedUnit: Unit = response.value;
        this.units.splice(index, 1);
        this.unitsChanged.next(this.getUnits());
      }
    );
  }

  fetchData(page: number, size: number) {
    let param = '';
    if (page) {
      param += '?page=' + page + '&size=' + size;
    }
    return this.http.get<ResponseRs>(environment.api_url + '/unidades-medida' + param);
  }

  saveData(unit: Unit) {
    return this.http.post<ResponseRs>(environment.api_url + '/unidades-medida', unit);
  }

  updateData(unit: Unit) {
    return this.http.put<ResponseRs>(environment.api_url + '/unidades-medida/' + unit.id, unit);
  }

  deleteData(unitId: number) {
    return this.http.delete<ResponseRs>(environment.api_url + '/unidades-medida/' + unitId);
  }
}
