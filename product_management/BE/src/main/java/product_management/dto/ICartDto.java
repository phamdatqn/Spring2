package product_management.dto;

public interface ICartDto {
    Integer getId();
    String getCustomerName();
    String getName();
    String getProductSizeId();
    String getSize();
    Integer getPrice();
    Integer getDiscount();
    Integer getQuantity();
    String getImage();
    Integer getTotal();
    Integer getTotalBill();
    String getDayPayment();
}
