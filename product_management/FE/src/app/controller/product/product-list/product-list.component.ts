import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {IProductDto} from '../../../dto/i-product-dto';
import {BehaviorSubject, Observable} from 'rxjs';
import {ProductService} from '../../../service/product.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {
  searchFormGroup: FormGroup = new FormGroup({
    nameSearch: new FormControl('')
  });
  productList: IProductDto[];
  nameSearch = '';
  page = 1;
  pageSize = 5;
  action: boolean;
  total$: Observable<number>;
  productNameDelete: string;
  productIdDelete: number;
  constructor(private productService: ProductService) {
  }

  ngOnInit(): void {
  }
  paginate() {
    this.productService.paginate(this.page, this.pageSize, this.nameSearch).subscribe(data => {
      if (data != null) {
        this.action = true;
        this.productList = data.content;
        this.total$ = new BehaviorSubject<number>(data.totalElements);
      } else {
        this.action = false;
      }
    });
  }

  getInfoSavingDelete(id: number, name: string): void {
    this.productIdDelete = id;
    this.productNameDelete = name;
  }

  deleteProduct() {
    this.productService.delete(this.productIdDelete).subscribe();
    // @ts-ignore
    Swal.fire({
      position: 'top-mid',
      icon: 'success',
      title: 'Đã xóa thành công !',
      showConfirmButton: false,
      timer: 1500
    });
  }

}
