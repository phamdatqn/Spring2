import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {ProductModule} from './controller/product/product.module';
import {SecurityModule} from './controller/security/security.module';


const routes: Routes = [
  {
    path: 'home', loadChildren: () => ProductModule,
  },
  {
    path: 'login', loadChildren: () => SecurityModule,
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
