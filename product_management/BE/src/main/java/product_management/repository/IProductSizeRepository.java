package product_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import product_management.model.ProductSize;

import java.util.List;

public interface IProductSizeRepository extends JpaRepository<ProductSize, Integer> {
    List<ProductSize> findAll();
}
