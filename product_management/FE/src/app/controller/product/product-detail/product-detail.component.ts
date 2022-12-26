import {Component, OnInit} from '@angular/core';
import {ProductService} from '../../../service/product.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Title} from '@angular/platform-browser';
import {Product} from '../../../model/product';
import {IProductSizeDto} from '../../../dto/iproduct-size-dto';
import {TokenStorageService} from '../../../service/token-storage.service';
import Swal from 'sweetalert2';
import {HttpEvent} from '@angular/common/http';
import {IProductDto} from '../../../dto/i-product-dto';

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent implements OnInit {
  quantityChoose = 1;
  productSizeIdChoose = 0;
  productSizeList: IProductSizeDto[];
  product: IProductDto;
  id: number;
  username;

  constructor(private productService: ProductService,
              private activatedRoute: ActivatedRoute,
              private tokenService: TokenStorageService,
              private title: Title,
              private router: Router) {
  }

  ngOnInit(): void {
    this.getProduct();
    this.getALlProductSize();
    this.getCustomer();
  }

  getProduct(): void {
    this.title.setTitle('Chi tiết sản phẩm');
    this.id = Number(this.activatedRoute.snapshot.params.id);
    this.productService.findById(this.id).subscribe(value => {
      this.product = value as IProductDto;
    });
  }

  getCustomer(): void {
    this.username = this.tokenService.getUser().username;
  }

  chooseProductSize(id: number): void {
    this.quantityChoose = 1;
    this.productSizeIdChoose = id;
  }

  getALlProductSize(): void {
    this.productService.findAllSizeByIdProduct(this.id).subscribe(sizeList => {
      this.productSizeList = sizeList as unknown as IProductSizeDto[]  ;
      console.log('this.productSizeList ');
      console.log(this.productSizeList );
    });
  }

  addCart(): void {
    if (this.username == null) {
      Swal.fire({
        position: 'center',
        icon: 'warning',
        title: 'Bạn chưa đăng nhập, vui lòng đăng nhập trước !',
        showConfirmButton: false,
        timer: 2000
      });
      this.router.navigateByUrl('/login');
    } else {
      this.productService.addToCart(this.username, this.productSizeIdChoose, this.quantityChoose).subscribe(value => {
        Swal.fire({
          position: 'center',
          icon: 'success',
          title: 'Thêm giỏ hàng thành công !',
          showConfirmButton: false,
          timer: 1000
        }).then(r => window.location.replace('product'));
      });
    }
  }

}
