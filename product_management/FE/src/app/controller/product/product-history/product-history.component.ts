import { Component, OnInit } from '@angular/core';
import {ProductService} from '../../../service/product.service';
import {TokenStorageService} from '../../../service/token-storage.service';
import {Router} from '@angular/router';
import {Title} from '@angular/platform-browser';
import {Observable} from 'rxjs';
import {ICartDto} from '../../../dto/i-cart-dto';
import {HttpEvent} from '@angular/common/http';

@Component({
  selector: 'app-product-history',
  templateUrl: './product-history.component.html',
  styleUrls: ['./product-history.component.css']
})
export class ProductHistoryComponent implements OnInit {
  username: string;
  cartHistory: HttpEvent<ICartDto[]>;
  constructor(private productService: ProductService,
              private tokenService: TokenStorageService,
              private router: Router,
              private title: Title) {
    this.title.setTitle('Lịch Sử Mua Hàng');
  }

  ngOnInit(): void {
    this.getUsername();
    this.getHistory();
  }

  getHistory(): void {
    this.productService.history(this.username).subscribe(value => {
      this.cartHistory = value;
      console.log(this.cartHistory);
    });
  }

  getUsername(): void {
    this.username = this.tokenService.getUser().username;
  }

}
