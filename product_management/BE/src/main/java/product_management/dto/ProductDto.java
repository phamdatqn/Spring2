package product_management.dto;

public class ProductDto {
    private Integer id;
    private String name;
    private int price;
    private int discount;
    private String manufacturer;
    private String describe;
    private String image;

    public ProductDto() {
    }

    public ProductDto(Integer id, String name, int price, int discount, String manufacturer, String describe, String image, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.manufacturer = manufacturer;
        this.describe = describe;
        this.image = image;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
