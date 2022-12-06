package product_management.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import product_management.model.Product;
import product_management.repository.IProductRepository;
import product_management.service.IProductService;

@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductRepository productRepository;

    @Override
    public Page<Product> findAllProductByName(Pageable pageable, String nameSearch) {
        return productRepository.findAllProductByName(pageable, nameSearch);
    }

    @Override
    public Page<Product> findAllProductByPrice(Pageable pageable, int primeSearch) {
        return productRepository.findAllProductByPrice(pageable, primeSearch);
    }
}
