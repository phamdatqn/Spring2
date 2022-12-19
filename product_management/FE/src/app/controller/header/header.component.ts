import {Component, OnInit} from '@angular/core';
import {TokenStorageService} from '../../service/token-storage.service';
import {Router} from '@angular/router';
import Swal from 'sweetalert2';
import {ProductService} from '../../service/product.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  username: string;
  roles: string[] = [];
  isCustomer = false;
  isAdmin = false;
  isEmployee = false;
  sumQuantityCart = 0;
  constructor(private tokenService: TokenStorageService,
              private productService: ProductService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.username = '';
    this.showUsername();
    this.getSumQuantity();
  }

  showUsername() {
    this.username = this.tokenService.getUser().username;
    // console.log('und');
    // console.log(this.tokenService.getUser());
    this.roles = this.tokenService.getUser().roles;
    this.isCustomer = this.roles.indexOf('ROLE_CUSTOMER') !== -1;
    this.isEmployee = this.roles.indexOf('ROLE_EMPLOYEE') !== -1;
    this.isAdmin = this.roles.indexOf('ROLE_ADMIN') !== -1;
  }

  getSumQuantity() {
    this.productService.sumQuantityCart(this.username).subscribe(value => {
      this.sumQuantityCart = value;
    });
  }


  whenLogout() {
    Swal.fire({
      position: 'center',
      icon: 'success',
      title: ' Đăng xuất thành công !',
      showConfirmButton: false,
      timer: 1000
    });
    this.tokenService.logOut();
    this.router.navigateByUrl('');
    this.username = '';
    this.isCustomer = false;
    this.isEmployee = false;
    this.isAdmin = false;
  }

  reload() {
    window.location.reload();
  }
}
