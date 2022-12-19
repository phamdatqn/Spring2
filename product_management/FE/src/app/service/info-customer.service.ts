import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ICustomer} from '../model/icustomer';

@Injectable({
  providedIn: 'root'
})
export class InfoCustomerService {
  private URL = 'http://localhost:8080/api/info';

  constructor(private httpClient: HttpClient) { }

  viewInfo(username: string): Observable<ICustomer> {
    return this.httpClient.get<ICustomer>(this.URL + '/' + username);
  }
}
