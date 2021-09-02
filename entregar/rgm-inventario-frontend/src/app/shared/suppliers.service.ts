import { Injectable } from "@angular/core";
import { Subject } from "rxjs/Subject";
import { HttpClient } from "@angular/common/http";
import { Supplier } from "./supplier.model";
import { ResponseRs } from "./response.model";
import { environment } from "../../environments/environment";

@Injectable()
export class SuppliersService {
  suppliers: Supplier[] = [];
  suppliersChanged = new Subject<Supplier[]>();

  constructor(private http: HttpClient) {}

  getSuppliers() {
    return this.suppliers.slice();
  }

  addSupplier(supplier: Supplier) {
    this.saveData(supplier).subscribe(
      (response) => {
        let savedSupplier: Supplier = response.value;
        this.suppliers.push(savedSupplier);
        this.suppliersChanged.next(this.getSuppliers());
      },
      err => console.error(err)
    );
  }

  addSuppliers(suppliers: Supplier[]) {
    this.suppliers.push(...suppliers);
    // this.suppliersChanged.next(this.getSuppliers());
  }

  updateSupplier(index: number, newSupplier: Supplier) {
    this.updateData(newSupplier).subscribe(
      (response) => {
        let updatedSupplier: Supplier = response.value;
        this.suppliers[index] = updatedSupplier;
        this.suppliersChanged.next(this.getSuppliers());
      }
    );
  }

  deleteSupplier(index: number, supplierId: number) {
    this.deleteData(supplierId).subscribe(
      (response) => {
        let deletedSupplier: Supplier = response.value;
        this.suppliers.splice(index, 1);
        this.suppliersChanged.next(this.getSuppliers());
      }
    );
  }

  fetchData() {
    return this.http.get<ResponseRs>(environment.api_url + '/proveedores');
  }

  saveData(supplier: Supplier) {
    return this.http.post<ResponseRs>(environment.api_url + '/proveedores', supplier);
  }

  updateData(supplier: Supplier) {
    return this.http.put<ResponseRs>(environment.api_url + '/proveedores/' + supplier.id, supplier);
  }

  deleteData(supplierId: number) {
    return this.http.delete<ResponseRs>(environment.api_url + '/proveedores/' + supplierId);
  }
}
