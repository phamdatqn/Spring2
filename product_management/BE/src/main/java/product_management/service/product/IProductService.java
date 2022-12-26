package product_management.service.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import product_management.dto.IProductDto;
import product_management.dto.ProductDto;
import product_management.model.Product;

import java.util.Optional;

public interface IProductService {
    Page<IProductDto> findAllProductByName(Pageable pageable, String nameSearch);

    Page<Product> findAllProductByPrice(Pageable pageable, int primeSearch);

    Optional<Product> findById(Integer idSearch);

    void updateProduct(ProductDto productDto);

    void deleteProduct(Integer id);
}
