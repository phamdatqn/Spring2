import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {SearchResult} from '../model/search-result';
import {Product} from '../model/product';
import {IProductDto} from '../dto/i-product-dto';
import {TokenStorageService} from './token-storage.service';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  username: string;
  roles: string[] = [];
  private PRODUCT_LIST_URL = 'http://localhost:8080/api/public/';
  constructor(private httpClient: HttpClient,
              private tokenStorageService: TokenStorageService) { }
  paginate(page: number, limit: number, nameSearch: string): Observable<SearchResult<Product>> {

    const URL = this.PRODUCT_LIST_URL + 'list?page=' + (page - 1) + '&size=' + limit +  '&nameSearch=' + nameSearch;
    console.log(URL);
    return this.httpClient.get<SearchResult<Product>>(URL);
  }

  delete(productIdDelete: number) {
    return this.httpClient.delete<void>(this.PRODUCT_LIST_URL  + productIdDelete);
  }

  findAllListProduct(nameSearch: string, size: number): Observable<SearchResult<IProductDto>> {
    const URL = this.PRODUCT_LIST_URL + 'list?size=' + size + '&nameSearch=' + nameSearch  ;
    console.log(URL);
    return this.httpClient.get<SearchResult<IProductDto>>(URL);
  }

  findById(id: number): Observable<IProductDto> {
    console.log(id);
    return this.httpClient.get<IProductDto>(this.PRODUCT_LIST_URL + 'detail/' + id);
  }
}
