import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {ProductListComponent} from './product-list/product-list.component';
import {ProductCreateComponent} from './product-create/product-create.component';
import {ProductEditComponent} from './product-edit/product-edit.component';
import {ProductDetailComponent} from './product-detail/product-detail.component';
import {ProductCartComponent} from './product-cart/product-cart.component';
import {ProductHistoryComponent} from './product-history/product-history.component';
import {AuthGuard} from '../security/auth.guard';


const routes: Routes = [
  {path: '', component: ProductListComponent},

  {path: 'create', component: ProductCreateComponent},

  {path: 'update/:id',
    canActivate: [AuthGuard],
    data: {
      roles: ['ROLE_ADMIN']
    }, component: ProductEditComponent},

  {path: 'detail/:id',
    component: ProductDetailComponent},

  {path: 'cart-list/:username',
    canActivate: [AuthGuard],
    data: {
      roles: ['ROLE_CUSTOMER', 'ROLE_ADMIN']
    },
    component: ProductCartComponent},

  {path: 'history/:username',
    canActivate: [AuthGuard],
    data: {
      roles: ['ROLE_CUSTOMER', 'ROLE_ADMIN']
    }, component: ProductHistoryComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProductRoutingModule { }
