package product_management.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import product_management.model.Product;

public interface IProductService {
    Page<Product> findAllProductByName(Pageable pageable, String nameSearch);
    Page<Product> findAllProductByPrice(Pageable pageable, int primeSearch);
}
