import {Component, OnInit} from '@angular/core';
import {IProductDto} from '../../../dto/i-product-dto';
import {BehaviorSubject, Observable} from 'rxjs';
import {ProductService} from '../../../service/product.service';
import {Router} from '@angular/router';
import {Title} from '@angular/platform-browser';
import {TokenStorageService} from '../../../service/token-storage.service';
import Swal from 'sweetalert2';

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
  idDelete: number;
  imageDelete: string;
  nameDelete: string;

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
      console.log(data);
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

  getInfoDelete(id: number, name: string, image: string) {
    this.idDelete = id;
    this.imageDelete = image;
    this.nameDelete = name;
  }

  deleteProduct(idDelete: number): void {
    this.productService.deleteProduct(idDelete).subscribe(value => {
      Swal.fire({
        position: 'center',
        icon: 'success',
        title: 'Xóa thành công !',
        showConfirmButton: false,
        timer: 1000
      }).then(r => window.location.reload());
    });
  }
}
