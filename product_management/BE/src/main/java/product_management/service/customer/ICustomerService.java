package product_management.service.customer;

import org.springframework.data.repository.query.Param;
import product_management.dto.IViewInfo;
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

    Customer viewInfo(String username);
}