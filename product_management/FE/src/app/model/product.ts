import {ProductSize} from './product-size';
import {ProductType} from './product-type';

export interface Product {
  id?: number;
  name?: string;
  price?: number;
  discount?: number;
  manufacturer?: string;
  describe?: string;
  image?: string;
  productType?: ProductType;
  productSize?: ProductSize;

}
