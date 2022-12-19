import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {SearchResult} from '../model/search-result';
import {IProductDto} from '../dto/i-product-dto';
import {TokenStorageService} from './token-storage.service';
import {IProductSizeDto} from '../dto/iproduct-size-dto';
import {ICartDto} from '../dto/i-cart-dto';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  username: string;
  roles: string[] = [];
  private URL = 'http://localhost:8080/api/public';

  constructor(private httpClient: HttpClient,
              private tokenStorageService: TokenStorageService) {
  }

  findAllListProduct(size: number, nameSearch: string): Observable<SearchResult<IProductDto>> {
    const URL = this.URL + '/list?size=' + size + '&nameSearch=' + nameSearch;
    return this.httpClient.get<SearchResult<IProductDto>>(URL);
  }

  findById(id: number): Observable<IProductDto> {
    return this.httpClient.get<IProductDto>(this.URL + '/detail/' + id);
  }

  findAllSizeByIdProduct(id: number): Observable<IProductSizeDto[]> {
    return this.httpClient.get<IProductSizeDto[]>(this.URL + '/product-size/' + id);
  }

  addToCart(username: string, productSizeId: number, quantity: number): Observable<void> {
    return this.httpClient.get<void>(this.URL + '/add-cart/' + username + '&' + productSizeId + '&' + quantity);
  }

  findAllCartListByUsername(username: string): Observable<ICartDto[]> {
    return this.httpClient.get<ICartDto[]>(this.URL + '/cart-list/' + username);
  }

  history(username: string): Observable<ICartDto[]> {
    const URL = this.URL + '/history/' + username;
    return this.httpClient.get<ICartDto[]>(URL);
  }

  totalBill(username: string): Observable<number> {
    return this.httpClient.get<number>(this.URL + '/total-bill/' + username);
  }

  descQuantity(id: number): Observable<void> {
    return this.httpClient.get<void>(this.URL + '/desc-quantity/' + id);
  }

  ascQuantity(id: number): Observable<void> {
    return this.httpClient.get<void>(this.URL + '/asc-quantity/' + id);
  }

  sumQuantityCart(username: string): Observable<number> {
    return this.httpClient.get<number>(this.URL + '/sum-quantity-cart/' + username);
  }

  deleteProduct(id: number): Observable<void> {
    return this.httpClient.delete<void>(this.URL + '/delete/' + id);
  }

  payment(username: string): Observable<void> {
    return this.httpClient.get<void>(this.URL + '/payment/' + username);
  }

}
