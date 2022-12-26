import {Injectable} from '@angular/core';
import {HttpClient, HttpEvent, HttpHeaders} from '@angular/common/http';
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
  httpOptions: any;
  private URL = 'http://localhost:8080/api/public';

  constructor(private httpClient: HttpClient,
              private tokenStorageService: TokenStorageService) {
    this.httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: 'Bearer ' + this.tokenStorageService.getToken()
      }),
      'Access-Control-Allow-Origin': 'http://localhost:4200',
      'Access-Control-Allow-Methods': 'GET,PUT,POST,DELETE,PATCH,OPTIONS'
    };
  }

  findAllListProduct(size: number, nameSearch: string): Observable<SearchResult<IProductDto>> {
    const URL = this.URL + '/list?size=' + size + '&nameSearch=' + nameSearch;
    return this.httpClient.get<SearchResult<IProductDto>>(URL);
  }

  findById(id: number): Observable<HttpEvent<IProductDto>> {
    return this.httpClient.get<IProductDto>(this.URL + '/detail/' + id, this.httpOptions);
  }

  findAllSizeByIdProduct(id: number): Observable<HttpEvent<IProductSizeDto[]>> {
    return this.httpClient.get<IProductSizeDto[]>(this.URL + '/product-size/' + id, this.httpOptions);
  }

  addToCart(username: string, productSizeId: number, quantity: number): Observable<HttpEvent<void>> {
    return this.httpClient.get<void>(this.URL + '/add-cart/' + username + '&' + productSizeId + '&' + quantity, this.httpOptions);
  }

  findAllCartListByUsername(username: string): Observable<HttpEvent<ICartDto[]>> {
    return this.httpClient.get<ICartDto[]>(this.URL + '/cart-list/' + username, this.httpOptions);
  }

  history(username: string): Observable<HttpEvent<ICartDto[]>> {
    const URL = this.URL + '/history/' + username;
    return this.httpClient.get<ICartDto[]>(URL, this.httpOptions);
  }

  totalBill(username: string): Observable<HttpEvent<number>> {
    return this.httpClient.get<number>(this.URL + '/total-bill/' + username, this.httpOptions);
  }

  descQuantity(id: number): Observable<HttpEvent<void>> {
    return this.httpClient.get<void>(this.URL + '/desc-quantity/' + id, this.httpOptions);
  }

  ascQuantity(id: number): Observable<HttpEvent<void>> {
    return this.httpClient.get<void>(this.URL + '/asc-quantity/' + id, this.httpOptions);
  }

  sumQuantityCart(username: string): Observable<HttpEvent<number>> {
    return this.httpClient.get<number>(this.URL + '/sum-quantity-cart/' + username, this.httpOptions);
  }

  deleteCart(id: number): Observable<HttpEvent<void>> {
    return this.httpClient.delete<void>(this.URL + '/delete-cart/' + id, this.httpOptions);
  }

  payment(username: string): Observable<HttpEvent<void>> {
    return this.httpClient.get<void>(this.URL + '/payment/' + username, this.httpOptions);
  }

  updateProduct(productDto: IProductDto): Observable<HttpEvent<IProductDto>> {
    console.log('3' + this.httpClient.patch<IProductDto>(this.URL + '/update' , productDto, this.httpOptions));
    return this.httpClient.patch<IProductDto>(this.URL + '/update' , productDto, this.httpOptions);
  }

  deleteProduct(id: number): Observable<HttpEvent<void>> {
    return this.httpClient.delete<void>(this.URL + '/delete/' + id, this.httpOptions);
  }
}
