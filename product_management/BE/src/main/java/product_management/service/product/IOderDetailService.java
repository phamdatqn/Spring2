package product_management.service.product;

import org.springframework.data.repository.query.Param;
import product_management.dto.ICartDto;

import java.util.List;

public interface IOderDetailService {
    void addCart(String username, Integer productSizeId, Integer quantity);

    List<ICartDto> findAllCartByUsername(String username);

    List<ICartDto> findAllHistory(String username);


    void descQuantity(Integer id);

    void ascQuantity(Integer id);

    Integer totalBill(String username);

    Integer sumQuantityCart(String username);

    void deleteProduct(Integer id);

    void payment(String username);
}
