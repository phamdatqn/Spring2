package product_management.service.product.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import product_management.dto.IProductDto;
import product_management.dto.ProductDto;
import product_management.model.Product;
import product_management.repository.IProductRepository;
import product_management.service.product.IProductService;

import java.util.Optional;

@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductRepository productRepository;

    @Override
    public Page<IProductDto> findAllProductByName(Pageable pageable, String nameSearch) {
        return productRepository.findAllProductByName(pageable, nameSearch);
    }

    @Override
    public Page<Product> findAllProductByPrice(Pageable pageable, int primeSearch) {
        return productRepository.findAllProductByPrice(pageable, primeSearch);
    }

    @Override
    public Optional<Product> findById(Integer idSearch) {
        return productRepository.findById(idSearch);
    }

    @Override
    public void updateProduct(ProductDto productDto) {
        productRepository.updateProduct(productDto);
    }

    @Override
    public void deleteProduct(Integer id) {
        productRepository.deleteProduct(id);
    }
}
