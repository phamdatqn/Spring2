import {Component, OnInit} from '@angular/core';
import {ProductService} from '../../../service/product.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Title} from '@angular/platform-browser';
import {Product} from '../../../model/product';

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent implements OnInit {

  product: Product;
  constructor(private productService: ProductService,
              private activatedRoute: ActivatedRoute,
              private title: Title,
              private router: Router) {
  }

  ngOnInit(): void {
    this.title.setTitle('Chi tiết sản phẩm');
    const id = Number(this.activatedRoute.snapshot.params.id);
    this.productService.findById(id).subscribe(value => {
      console.log(value);
      this.product = value;
      // this.url = this.transform(this.movie.trailer);
    });
  }
}
