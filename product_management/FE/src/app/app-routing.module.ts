import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {ProductModule} from './controller/product/product.module';


const routes: Routes = [
  {
    path: 'product', loadChildren: () => ProductModule,
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
