import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProductRoutingModule } from './product-routing.module';
import {ProductListComponent} from './product-list/product-list.component';
import {ProductCreateComponent} from './product-create/product-create.component';
import {ProductEditComponent} from './product-edit/product-edit.component';
import { ProductDetailComponent } from './product-detail/product-detail.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { ProductCartComponent } from './product-cart/product-cart.component';
import { ProductHistoryComponent } from './product-history/product-history.component';


@NgModule({
  declarations: [
    ProductListComponent,
    ProductCreateComponent,
    ProductEditComponent,
    ProductDetailComponent,
    ProductCartComponent,
    ProductHistoryComponent
  ],
  imports: [
    CommonModule,
    ProductRoutingModule,
    ReactiveFormsModule,
    FormsModule
  ]
})
export class ProductModule { }
