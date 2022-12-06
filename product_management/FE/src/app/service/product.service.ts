import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {SearchResult} from '../model/search-result';
import {Product} from '../model/product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private PRODUCT_LIST_URL = 'http://localhost:8080/api/product/';
  constructor(private httpClient: HttpClient) { }
  paginate(page: number, limit: number, nameSearch: string): Observable<SearchResult<Product>> {

    const URL = this.PRODUCT_LIST_URL + 'list?page=' + (page - 1) + '&size=' + limit +  '&nameSearch=' + nameSearch;
    console.log(URL);
    return this.httpClient.get<SearchResult<Product>>(URL);
  }

  delete(productIdDelete: number) {
    return this.httpClient.delete<void>(this.PRODUCT_LIST_URL  + productIdDelete);
  }
}
