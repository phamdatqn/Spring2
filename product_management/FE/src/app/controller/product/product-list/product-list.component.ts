import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {IProductDto} from '../../../dto/i-product-dto';
import {BehaviorSubject, Observable} from 'rxjs';
import {ProductService} from '../../../service/product.service';
import {Router} from '@angular/router';
import {Title} from '@angular/platform-browser';

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
  constructor(private productService: ProductService,
              private router: Router,
              private title: Title) {
    this.title.setTitle('Trang chủ');
  }

  ngOnInit(): void {
    this.paginate();
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
