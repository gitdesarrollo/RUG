import { Injectable } from "@angular/core";
import { Subject } from "rxjs/Subject";
import { HttpClient } from "@angular/common/http";
import { Brand } from "./brand.model";
import { ResponseRs } from "./response.model";
import { environment } from "../../environments/environment";

@Injectable()
export class BrandsService {
  brands: Brand[] = [];
  brandsChanged = new Subject<Brand[]>();

  constructor(private http: HttpClient) {}

  getBrands() {
    return this.brands.slice();
  }

  addBrand(brand: Brand) {
    this.saveData(brand).subscribe(
      (response) => {
        let savedBrand: Brand = response.value;
        this.brands.push(savedBrand);
        this.brandsChanged.next(this.getBrands());
      },
      err => console.error(err)
    );
  }

  addBrands(brands: Brand[]) {
    this.brands.push(...brands);
    // this.brandsChanged.next(this.getBrands());
  }

  updateBrand(index: number, newBrand: Brand) {
    this.updateData(newBrand).subscribe(
      (response) => {
        let updatedBrand: Brand = response.value;
        this.brands[index] = updatedBrand;
        this.brandsChanged.next(this.getBrands());
      }
    );
  }

  deleteBrand(index: number, brandId: number) {
    this.deleteData(brandId).subscribe(
      (response) => {
        let updatedBrand: Brand = response.value;
        this.brands.splice(index, 1);
        this.brandsChanged.next(this.getBrands());
      }
    );
  }

  fetchData(page: number, size: number) {
    let param = '';
    if (page) {
      param += '?page=' + page + '&size=' + size;
    }
    return this.http.get<ResponseRs>(environment.api_url + '/marcas' + param);
  }

  saveData(brand: Brand) {
    return this.http.post<ResponseRs>(environment.api_url + '/marcas', brand);
  }

  updateData(brand: Brand) {
    return this.http.put<ResponseRs>(environment.api_url + '/marcas/' + brand.id, brand);
  }

  deleteData(brandId: number) {
    return this.http.delete<ResponseRs>(environment.api_url + '/marcas/' + brandId);
  }
}
