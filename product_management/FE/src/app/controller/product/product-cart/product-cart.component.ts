import {Component, OnInit} from '@angular/core';
import {ProductService} from '../../../service/product.service';
import {Router} from '@angular/router';
import {Title} from '@angular/platform-browser';
import {ICartDto} from '../../../dto/i-cart-dto';
import {TokenStorageService} from '../../../service/token-storage.service';
import Swal from 'sweetalert2';
import {render} from 'creditcardpayments/creditCardPayments';

@Component({
  selector: 'app-product-cart',
  templateUrl: './product-cart.component.html',
  styleUrls: ['./product-cart.component.css']
})
export class ProductCartComponent implements OnInit {

  oderDetailList: ICartDto[];
  username: string;
  totalBill = 0;
  idDelete: number;
  imageDelete: string;
  nameDelete: string;
  sizeDelete: string;
  constructor(private productService: ProductService,
              private tokenService: TokenStorageService,
              private router: Router,
              private title: Title) {
    this.title.setTitle('');
    render(
      {
        id: '#myPaypal',
        value: '55',
        currency: 'USD',
        onApprove: (details) => {
          alert('thành công');
        }
      }
    );
  }

  ngOnInit(): void {
    this.getUsername();
    this.getAllOderDetail();
    this.getTotalBill();
    this.getInfoDelete(this.idDelete, this.nameDelete, this.imageDelete, this.sizeDelete);
  }

  getUsername(): void {
    this.username = this.tokenService.getUser().username;
  }

  getAllOderDetail(): void {
    this.productService.findAllCartListByUsername(this.username).subscribe(value => {
      this.oderDetailList = value;
    });
  }

  descQuantity(id: number): void {
    this.productService.descQuantity(id).subscribe(() => {
      this.getAllOderDetail();
      this.getTotalBill();
    });
  }

  ascQuantity(id: number): void {
    this.productService.ascQuantity(id).subscribe(value => {
      this.getAllOderDetail();
      this.getTotalBill();
    });
  }

  getTotalBill(): void {
    this.productService.totalBill(this.username).subscribe( value => {
      this.totalBill = value;
    });
  }

  removeCart(id: number) {
  }

  getInfoDelete(id: number, name: string, image: string, size: string) {
    this.idDelete = id;
    this.imageDelete = image;
    this.nameDelete = name;
    this.sizeDelete = size;
  }

  deleteProduct(idDelete: number): void {
    this.productService.deleteProduct(idDelete).subscribe(value => {
      Swal.fire({
        position: 'center',
        icon: 'success',
        title:  'Xóa thành công !',
        showConfirmButton: false,
        timer: 1000
      });
      window.location.reload();
    });
  }
}
