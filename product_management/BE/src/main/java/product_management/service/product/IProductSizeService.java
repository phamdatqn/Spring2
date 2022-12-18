package product_management.service.product;

import product_management.dto.IProductSizeDto;

import java.util.List;

public interface IProductSizeService {
    List<IProductSizeDto> findAllSizeByIdProduct(Integer id);
}
