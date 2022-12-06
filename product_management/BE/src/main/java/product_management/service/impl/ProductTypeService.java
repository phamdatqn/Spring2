package product_management.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import product_management.model.ProductType;
import product_management.repository.IProductTypeRepository;
import product_management.service.IProductTypeService;

import java.util.List;

@Service
public class ProductTypeService implements IProductTypeService {

    @Autowired
    private IProductTypeRepository productTypeRepository;
    @Override
    public List<ProductType> findAllProductType() {
        return productTypeRepository.findAll();
    }
}
