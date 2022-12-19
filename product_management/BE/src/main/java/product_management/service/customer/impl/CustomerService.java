package product_management.service.customer.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import product_management.dto.IViewInfo;
import product_management.model.Customer;
import product_management.repository.ICustomerRepository;
import product_management.service.customer.ICustomerService;

import java.util.List;

@Service
public class CustomerService implements ICustomerService {


    @Autowired
    private ICustomerRepository customerRepository;

    /**
     * Đạt
     */
    @Override
    public Customer findFakeMail(String email) {
        return customerRepository.findFakeMail(email);
    }

    @Override
    public int saveCreateGmail(Customer customer) {
        return customerRepository.saveCreateGmail(customer.getName(), customer.getEmail());
    }

    @Override
    public Customer findById(Integer id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public List<Integer> findAllCusId() {
        return customerRepository.findAllCustomerIdById();
    }

    @Override
    public Customer findByUsername(String username) {
        return customerRepository.findByUsername(username);
    }

    @Override
    public Customer viewInfo(String username) {
        return customerRepository.viewInfo(username);
    }
}