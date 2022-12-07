package product_management.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import product_management.dto.IProductDto;
import product_management.model.Product;

import java.util.Optional;

public interface IProductRepository extends JpaRepository<Product, Integer> {
    @Query(value = "select * from product where name like %:nameSearch% and product_size_id = 1 and is_delete = 0 ",
            countQuery = "select count(*) from product where name like %:nameSearch% and product_size_id = 1 and is_delete = 0 ",nativeQuery = true)
    Page<Product> findAllProductByName(Pageable pageable,
                                     @Param("nameSearch") String nameSearch);

    @Query(value = "select * from product where price =:priceSearch and is_delete = 0 ",
            countQuery = "select count(*) from product where price =:priceSearch and is_delete = 0 ",nativeQuery = true)
    Page<Product> findAllProductByPrice(Pageable pageable,
                                       @Param("priceSearch") int priceSearch);

    @Query(value = "select * from product where id =:idSearch and is_delete = 0 ",
            countQuery = "select count(*) from product where id =:idSearch and is_delete = 0 ",nativeQuery = true)
    Optional<Product> findById(@Param("idSearch") Integer id);
}
