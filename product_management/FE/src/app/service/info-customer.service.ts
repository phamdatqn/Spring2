import { Injectable } from '@angular/core';
import {HttpClient, HttpEvent, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ICustomer} from '../model/icustomer';
import {TokenStorageService} from './token-storage.service';

@Injectable({
  providedIn: 'root'
})
export class InfoCustomerService {
  private URL = 'http://localhost:8080/api/info/';
  httpOptions: any;
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

  viewInfo(username: string): Observable<HttpEvent<ICustomer>> {
    return this.httpClient.get<ICustomer>(this.URL + username, this.httpOptions);
  }
}
