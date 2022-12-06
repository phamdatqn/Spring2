import {ProductType} from '../model/product-type';
import {ProductSize} from '../model/product-size';

export interface IProductDto {
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
