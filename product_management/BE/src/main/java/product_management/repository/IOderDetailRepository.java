package product_management.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import product_management.dto.ICartDto;
import product_management.model.OrderDetail;

import java.util.List;

@Transactional
public interface IOderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    @Modifying
    @Query(value = "insert into order_detail(customer_id,product_size_id,quantity,day_payment) " +
            "values (:customerId, :productSizeId, :quantity, now())", nativeQuery = true)
    void addCart(@Param("customerId") Integer customerId,
                 @Param("productSizeId") Integer productSizeId,
                 @Param("quantity") Integer quantity);

    @Query(value = "select order_detail.id as id, customer.name as customerName, product.name as name, " +
            "order_detail.product_size_id as productSizeId, size.name as size, product.price as price, " +
            "product.discount as Discount, order_detail.quantity as quantity, product.image as image, " +
            "(product.price * order_detail.quantity) as total " +
            "from order_detail " +
            "join customer on order_detail.customer_id = customer.id " +
            "join product_size on order_detail.product_size_id = product_size.id " +
            "join product on product.id = product_size.product_id " +
            "join size on product_size.size_id = size.id " +
            "where username = :username and order_detail.is_delete = 0 and is_pay = 0 " +
            "group by order_detail.id", nativeQuery = true)
    List<ICartDto> findAllCartByUsername(@Param("username") String username);


    @Query(value = "select * " +
            "from order_detail " +
            "where customer_id =:customerId and is_delete = 0 and is_pay = 0 " +
            "and product_size_id =:productSizeId ", nativeQuery = true)
    OrderDetail checkOderDetailByUsername(@Param("customerId") Integer customerId,
                                          @Param("productSizeId") Integer productSizeId);

    @Modifying
    @Query(value = "update order_detail set quantity =:quantity " +
            "where customer_id =:customerId " +
            "and product_size_id =:productSizeId " +
            "and is_delete = 0 and is_pay = 0 ", nativeQuery = true)
    void setQuantity(@Param("customerId") Integer customerId,
                     @Param("productSizeId") Integer productSizeId,
                     @Param("quantity") Integer quantity);


    @Modifying
    @Query(value = "update order_detail set quantity = (quantity - 1) " +
            "where id =:id and is_delete = 0 and is_pay = 0 ", nativeQuery = true)
    void descQuantity(@Param("id") Integer id);

    @Modifying
    @Query(value = "update order_detail set quantity = (quantity + 1) " +
            "where id =:id and is_delete = 0 and is_pay = 0 ", nativeQuery = true)
    void ascQuantity(@Param("id") Integer id);

    @Query(value = "select sum(product.price * order_detail.quantity) as totalBill " +
            "from order_detail " +
            "join customer on order_detail.customer_id = customer.id " +
            "join product_size on order_detail.product_size_id = product_size.id " +
            "join product on product.id = product_size.product_id " +
            "join size on product_size.size_id = size.id " +
            "where username = :username and order_detail.is_delete = 0 and is_pay = 0 ", nativeQuery = true)
    Integer totalBill(@Param("username") String username);

    @Query(value = "select sum(quantity) as sumQuantityCart " +
            "from order_detail " +
            "where is_pay = 0 and customer_id = :id",
            nativeQuery = true)
    Integer sumQuantityCart(@Param("id") Integer id);

    @Modifying
    @Query(value = "delete from order_detail where id =:id ",nativeQuery = true)
    void deleteCart(@Param("id")Integer id);

    @Modifying
    @Query(value = "update order_detail set is_pay = 1 where customer_id =:customerId ",nativeQuery = true)
    void payment(@Param("customerId") Integer customerId);


    @Query(value = "select order_detail.id as id, customer.name as customerName, product.name as name, " +
            "order_detail.product_size_id as productSizeId, order_detail.day_payment as dayPayment,size.name as size, product.price as price, " +
            "product.discount as Discount, order_detail.quantity as quantity, product.image as image, " +
            "(product.price * order_detail.quantity) as total " +
            "from order_detail " +
            "join customer on order_detail.customer_id = customer.id " +
            "join product_size on order_detail.product_size_id = product_size.id " +
            "join product on product.id = product_size.product_id " +
            "join size on product_size.size_id = size.id " +
            "where username = :username and order_detail.is_delete = 0 and is_pay = 1 ", nativeQuery = true)
    List<ICartDto> findAllHistory(@Param("username") String username);
}
