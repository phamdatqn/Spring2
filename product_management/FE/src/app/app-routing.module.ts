import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {ProductModule} from './controller/product/product.module';
import {SecurityModule} from './controller/security/security.module';
import {InfoCustomerModule} from './controller/view-info/info-customer.module';


const routes: Routes = [
  {
    path: 'product', loadChildren: () => ProductModule,
  },
  {
    path: 'login', loadChildren: () => SecurityModule,
  },
  {
    path: 'view', loadChildren: () => InfoCustomerModule,
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
