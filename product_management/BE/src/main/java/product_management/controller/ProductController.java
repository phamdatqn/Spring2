package product_management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import product_management.dto.ICartDto;
import product_management.dto.IProductDto;
import product_management.dto.IProductSizeDto;
import product_management.dto.ProductDto;
import product_management.model.OrderDetail;
import product_management.model.Product;
import product_management.service.product.IOderDetailService;
import product_management.service.product.IProductService;
import product_management.service.product.IProductSizeService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/public/")
public class ProductController {

    @Autowired
    private IProductService productService;

    @Autowired
    private IProductSizeService productSizeService;

    @Autowired
    private IOderDetailService oderDetailService;

    @GetMapping("list")
    public ResponseEntity<Page<IProductDto>> showListNameSearch(@PageableDefault(value = 4) Pageable pageable,
                                                                @RequestParam(value = "nameSearch", defaultValue = "") String nameSearch) {
        Page<IProductDto> productDtoPage = productService.findAllProductByName(pageable, nameSearch);
        if (productDtoPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productDtoPage, HttpStatus.OK);
    }

    @GetMapping("history/{username}")
    public ResponseEntity<List<ICartDto>> showHistory(@PathVariable("username") String username) {
        List<ICartDto> cartDtoList = oderDetailService.findAllHistory(username);
        if (cartDtoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cartDtoList, HttpStatus.OK);
    }

    @GetMapping(value = "detail/{id}")
    public ResponseEntity<Optional<Product>> detailProduct(@PathVariable Integer id) {
        Optional<Product> product = productService.findById(id);
        if (!product.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("product-size/{id}")
    public ResponseEntity<List<IProductSizeDto>> showListProductSize(@PathVariable("id") Integer id) {
        List<IProductSizeDto> shoeSizeList = productSizeService.findAllSizeByIdProduct(id);
        return new ResponseEntity<>(shoeSizeList, HttpStatus.OK);
    }

    @GetMapping("add-cart/{username}&{productSizeId}&{quantity}")
    public ResponseEntity<OrderDetail> addToCart(@PathVariable("username") String username,
                                                 @PathVariable("productSizeId") Integer productSizeId,
                                                 @PathVariable("quantity") Integer quantity) {
        oderDetailService.addCart(username, productSizeId, quantity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("cart-list/{username}")
    public ResponseEntity<List<ICartDto>> showCartList(@PathVariable("username") String username) {
        List<ICartDto> oderDetailDtoList = oderDetailService.findAllCartByUsername(username);
        if (oderDetailDtoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(oderDetailDtoList, HttpStatus.OK);
    }

    @GetMapping("desc-quantity/{id}")
    public ResponseEntity<OrderDetail> descQuantity(@PathVariable("id") Integer id) {
        oderDetailService.descQuantity(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("asc-quantity/{id}")
    public ResponseEntity<OrderDetail> ascQuantity(@PathVariable("id") Integer id) {
        oderDetailService.ascQuantity(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("total-bill/{username}")
    public ResponseEntity<Integer> totalBill(@PathVariable("username") String username) {
        Integer totalBill = oderDetailService.totalBill(username);
        return new ResponseEntity<>(totalBill, HttpStatus.OK);
    }

    @GetMapping("sum-quantity-cart/{username}")
    public ResponseEntity<Integer> sumQuantity(@PathVariable("username") String username) {
        Integer sumQuantityCart = oderDetailService.sumQuantityCart(username);
        return new ResponseEntity<>(sumQuantityCart, HttpStatus.OK);
    }

    @DeleteMapping("delete-cart/{id}")
    public ResponseEntity<Integer> deleteCart(@PathVariable("id") Integer id) {
        oderDetailService.deleteCart(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("payment/{username}")
    public ResponseEntity<OrderDetail> payment(@PathVariable("username") String username) {
        oderDetailService.payment(username);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("update")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody @Valid ProductDto productDto) {
        Optional<Product> product = productService.findById(productDto.getId());
        if (product.isPresent()) {
            productService.updateProduct(productDto);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Integer> deleteProduct(@PathVariable("id") Integer id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}