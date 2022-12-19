package product_management.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import product_management.dto.IViewInfo;
import product_management.model.Customer;

import java.util.List;

@Repository
@Transactional
public interface ICustomerRepository extends JpaRepository<Customer, Integer> {

    /*
    Đạt phạm
     */
    @Query(value = "select * from customer where email = :email and is_delete = 0 LIMIT 0, 1;", nativeQuery = true)
    Customer findFakeMail(@Param("email") String email);

    @Modifying
    @Transactional
    @Query(value = "insert into customer(name,email,username) values (:name, :email, :email)", nativeQuery = true)
    int saveCreateGmail(@Param("name") String name, @Param("email") String email);

    @Query(value = "select customer.id from customer", nativeQuery = true)
    List<Integer> findAllCustomerIdById();

    @Query(value = "select * from customer where username = :username and is_delete = 0;", nativeQuery = true)
    Customer findByUsername(@Param("username") String username);

    @Query(value = "select * from customer where username =:username and is_delete = 0 ", nativeQuery = true)
    Customer viewInfo(@Param("username")String username);
}