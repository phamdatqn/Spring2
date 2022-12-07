package product_management.service.customer;

import product_management.model.Customer;

import java.util.List;

public interface ICustomerService {

    /*
    Đạt Phạm
     */
    Customer findFakeMail(String email);

    int saveCreateGmail(Customer customer);

    Customer findById(Integer id);

    List<Integer> findAllCusId();

    Customer findByUsername(String username);


}