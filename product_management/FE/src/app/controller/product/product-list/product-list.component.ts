import {Component, OnInit} from '@angular/core';
import {IProductDto} from '../../../dto/i-product-dto';
import {BehaviorSubject, Observable} from 'rxjs';
import {ProductService} from '../../../service/product.service';
import {Router} from '@angular/router';
import {Title} from '@angular/platform-browser';
import {TokenStorageService} from '../../../service/token-storage.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {
  nameSearch = '';
  pageSize = 4;
  productList$: Observable<IProductDto[]> | undefined;
  total$: Observable<number>;
  action: boolean;
  numberRecord = 0;
  content: boolean;
  totalRecord = 0;
  productNameDelete: string;
  productIdDelete: number;
  roles: string[] = [];
  isCustomer = false;
  isAdmin = false;
  isEmployee = false;
  constructor(private productService: ProductService,
              private tokenService: TokenStorageService,
              private router: Router,
              private title: Title) {
    this.title.setTitle('Trang chủ');
  }

  ngOnInit(): void {
    this.paginate();
    this.getRoles();
  }

  getRoles() {
    this.roles = this.tokenService.getUser().roles;
    this.isCustomer = this.roles.indexOf('ROLE_CUSTOMER') !== -1;
    this.isEmployee = this.roles.indexOf('ROLE_EMPLOYEE') !== -1;
    this.isAdmin = this.roles.indexOf('ROLE_ADMIN') !== -1;
  }

  paginate() {
    this.productService.findAllListProduct(this.pageSize, this.nameSearch).subscribe(data => {
      if (data != null) {
        this.action = true;
        this.productList$ = new BehaviorSubject<IProductDto[]>(data.content);
        this.total$ = new BehaviorSubject<number>(data.totalElements);
      } else {
        this.action = false;
      }
    });
  }

  nextPage() {
    this.pageSize += 4;
    this.paginate();
  }

  // getInfoSavingDelete(id: number, name: string): void {
  //   this.productIdDelete = id;
  //   this.productNameDelete = name;
  // }
  //
  // deleteProduct() {
  //   this.productService.delete(this.productIdDelete).subscribe();
  //   // @ts-ignore
  //   Swal.fire({
  //     position: 'top-mid',
  //     icon: 'success',
  //     title: 'Đã xóa thành công !',
  //     showConfirmButton: false,
  //     timer: 1500
  //   });
  // }
}
