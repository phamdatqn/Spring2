package product_management.service.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import product_management.model.Product;

import java.util.Optional;

public interface IProductService {
    Page<Product> findAllProductByName(Pageable pageable, String nameSearch);
    Page<Product> findAllProductByPrice(Pageable pageable, int primeSearch);
    Optional<Product> findById(Integer idSearch);
}
