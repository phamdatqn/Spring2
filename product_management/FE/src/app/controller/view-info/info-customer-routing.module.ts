import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {ProductListComponent} from '../product/product-list/product-list.component';
import {InfoCustomerComponent} from './info-customer/info-customer.component';
import {AuthGuard} from '../security/auth.guard';


const routes: Routes = [
  {path: 'customer/:username',
    canActivate: [AuthGuard],
    data: {
      roles: ['ROLE_CUSTOMER', 'ROLE_ADMIN']
    }, component: InfoCustomerComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class InfoCustomerRoutingModule { }
