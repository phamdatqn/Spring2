import { Component, OnInit } from '@angular/core';
import {ICustomer} from '../../../model/icustomer';
import {TokenStorageService} from '../../../service/token-storage.service';
import {InfoCustomerService} from '../../../service/info-customer.service';
import {HttpEvent} from '@angular/common/http';

@Component({
  selector: 'app-info-customer',
  templateUrl: './info-customer.component.html',
  styleUrls: ['./info-customer.component.css']
})
export class InfoCustomerComponent implements OnInit {
  username: string;
  viewInfo: ICustomer;
  constructor(private infoCustomerService: InfoCustomerService,
              private tokenService: TokenStorageService) { }

  ngOnInit(): void {
    this.getUsername();
    this.getViewInfo();
  }

  getUsername(): void {
    this.username = this.tokenService.getUser().username;
  }

  getViewInfo(): void {
    this.infoCustomerService.viewInfo(this.username).subscribe(value => {
      this.viewInfo = value as ICustomer;
    });
  }
}
