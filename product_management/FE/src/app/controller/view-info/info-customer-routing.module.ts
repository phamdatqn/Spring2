import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {ProductListComponent} from '../product/product-list/product-list.component';
import {InfoCustomerComponent} from './info-customer/info-customer.component';


const routes: Routes = [
  {path: 'customer/:username', component: InfoCustomerComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class InfoCustomerRoutingModule { }
