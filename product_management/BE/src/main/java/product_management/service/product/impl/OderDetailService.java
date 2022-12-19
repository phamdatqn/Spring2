package product_management.service.product.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import product_management.dto.ICartDto;
import product_management.model.Customer;
import product_management.model.OrderDetail;
import product_management.repository.ICustomerRepository;
import product_management.repository.IOderDetailRepository;
import product_management.service.product.IOderDetailService;

import java.util.List;

@Service
public class OderDetailService implements IOderDetailService {

    @Autowired
    private IOderDetailRepository oderDetailRepository;

    @Autowired
    private ICustomerRepository customerRepository;

    @Override
    public void addCart(String username, Integer productSizeId, Integer quantity) {
        Customer customer = customerRepository.findByUsername(username);
        if (customer != null) {
            OrderDetail findOder = oderDetailRepository.checkOderDetailByUsername(customer.getId(), productSizeId);
            if (findOder == null) {
                oderDetailRepository.addCart(customer.getId(), productSizeId, quantity);
            } else {
                oderDetailRepository.setQuantity(customer.getId(), productSizeId, findOder.getQuantity() + quantity);
            }
        }
    }

    @Override
    public List<ICartDto> findAllCartByUsername(String username) {
        return oderDetailRepository.findAllCartByUsername(username);
    }

    @Override
    public List<ICartDto> findAllHistory(String username) {
        return oderDetailRepository.findAllHistory(username);
    }

    @Override
    public void descQuantity(Integer id) {
        oderDetailRepository.descQuantity(id);
    }

    @Override
    public void ascQuantity(Integer id) {
        oderDetailRepository.ascQuantity(id);
    }

    @Override
    public Integer totalBill(String username) {
        return oderDetailRepository.totalBill(username);
    }

    @Override
    public Integer sumQuantityCart(String username) {
        Customer customer = customerRepository.findByUsername(username);
        return oderDetailRepository.sumQuantityCart(customer.getId());
    }

    @Override
    public void deleteProduct(Integer id) {
        oderDetailRepository.deleteProduct(id);
    }

    @Override
    public void payment(String username) {
        Customer customer = customerRepository.findByUsername(username);
        oderDetailRepository.payment(customer.getId());
    }
}
