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
    @Query(value = "select product.id as id, product.name as name, product.price as price, " +
            "product.discount as discount,product.manufacturer as manufacturer, product.describe as `describe`, " +
            "product.image as image, product_type.name as productType, sum(product_size.quantity) as sumQuantity  " +
            "from product " +
            "join product_size on product.id = product_size.product_id " +
            "join product_type on product.product_type_id = product_type.id " +
            "where product.name like %:nameSearch%  and product.is_delete = 0 " +
            "group by product.id having sum(product_size.quantity) > 0",
            countQuery = "select count(*) , sum(product_size.quantity) " +
                    "from product " +
                    "join product_size on product.id = product_size.product_id " +
                    "join product_type on product.product_type_id = product_type.id " +
                    "where product.name like %:nameSearch%  and product.is_delete = 0 " +
                    "group by product.id having sum(product_size.quantity) > 0",nativeQuery = true)
    Page<IProductDto> findAllProductByName(Pageable pageable,
                                     @Param("nameSearch") String nameSearch);

    @Query(value = "select * from product where price =:priceSearch and is_delete = 0 ",
            countQuery = "select count(*) from product where price =:priceSearch and is_delete = 0 ",nativeQuery = true)
    Page<Product> findAllProductByPrice(Pageable pageable,
                                       @Param("priceSearch") int priceSearch);

    @Query(value = "select * from product where id =:idSearch and is_delete = 0 ",
            countQuery = "select count(*) from product where id =:idSearch and is_delete = 0 ",nativeQuery = true)
    Optional<Product> findById(@Param("idSearch") Integer id);
}
