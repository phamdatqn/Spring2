import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { InfoCustomerRoutingModule } from './info-customer-routing.module';
import {InfoCustomerComponent} from './info-customer/info-customer.component';


@NgModule({
  declarations: [
    InfoCustomerComponent
  ],
  imports: [
    CommonModule,
    InfoCustomerRoutingModule
  ]
})
export class InfoCustomerModule { }
