package product_management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import product_management.model.Product;
import product_management.service.IProductService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping("/list")
    public ResponseEntity<Page<Product>> showListNameSearch(@PageableDefault(value = 5) Pageable pageable,
                                                  @RequestParam(value = "nameSearch", defaultValue = "") String nameSearch) {
        Page<Product> productPage = productService.findAllProductByName(pageable, nameSearch);
        if (productPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productPage, HttpStatus.OK);
    }
}
