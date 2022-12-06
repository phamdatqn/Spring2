package product_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import product_management.model.ProductType;

import java.util.List;

public interface IProductTypeRepository extends JpaRepository<ProductType, Integer> {
    List<ProductType> findAll();
}
