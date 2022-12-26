import {Component, OnInit} from '@angular/core';
import {ProductService} from '../../../service/product.service';
import {ActivatedRoute, Router} from '@angular/router';
import {TokenStorageService} from '../../../service/token-storage.service';
import {Title} from '@angular/platform-browser';
import {IProductDto} from '../../../dto/i-product-dto';
import {FormBuilder, FormGroup} from '@angular/forms';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-product-edit',
  templateUrl: './product-edit.component.html',
  styleUrls: ['./product-edit.component.css']
})
export class ProductEditComponent implements OnInit {
  product: IProductDto;
  id: number;
  editForm: FormGroup;

  constructor(private productService: ProductService,
              private activatedRoute: ActivatedRoute,
              private tokenService: TokenStorageService,
              private title: Title,
              private router: Router,
              private formBuild: FormBuilder) {
  }

  ngOnInit(): void {
    this.getProduct();
    this.editForm = this.formBuild.group({
      id: [],
      name: [],
      price: [],
      discount: [],
      manufacturer: [],
      describe: [],
      image: []
    });
  }

  getProduct(): void {
    this.title.setTitle('Chỉnh sửa sản phẩm');
    this.id = Number(this.activatedRoute.snapshot.params.id);
    this.productService.findById(this.id).subscribe(value => {
      this.product = value as IProductDto;
      this.editForm.patchValue(this.product);
    });
  }

  updateProduct(): void {
    this.productService.updateProduct(this.editForm.value).subscribe(value => {
      Swal.fire({
        position: 'center',
        icon: 'success',
        title: 'Cập nhật thành công !',
        showConfirmButton: false,
        timer: 1000
      });
    });
  }
}
