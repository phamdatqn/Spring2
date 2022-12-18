package product_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import product_management.dto.IProductSizeDto;
import product_management.model.ProductSize;

import java.util.List;

public interface IProductSizeRepository extends JpaRepository<ProductSize, Integer> {

    @Query(value = "select product_size.id as id, quantity as quantity, size.name as sizeName " +
            "from product_size " +
            "join size on product_size.size_id = size.id " +
            "join product on product_size.product_id = product.id " +
            "where product.id = :id and product.is_delete = 0 and product_size.quantity > 0 ", nativeQuery = true)
    List<IProductSizeDto> findAllSizeByIdProduct(@Param("id")Integer id);
}
