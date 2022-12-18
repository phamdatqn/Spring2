package product_management.service.product;

import product_management.dto.ICartDto;

import java.util.List;

public interface IOderDetailService {
    void addCart(String username, Integer productSizeId, Integer quantity);

    List<ICartDto> findAllCartByUsername(String username);

    void descQuantity(Integer id);

    void ascQuantity(Integer id);

    Integer totalBill(String username);

    Integer sumQuantityCart(String username);

    void deleteProduct(Integer id);
}
