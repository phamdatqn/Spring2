package product_management.service.product.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import product_management.model.ProductSize;
import product_management.repository.IProductSizeRepository;
import product_management.service.product.IProductSizeService;

import java.util.List;

@Service
public class ProductSizeService implements IProductSizeService {

    @Autowired
    private IProductSizeRepository productSizeRepository;

    @Override
    public List<ProductSize> findAllProductSize() {
        return productSizeRepository.findAll();
    }
}
