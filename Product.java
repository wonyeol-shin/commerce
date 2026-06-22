package commerce;

public class Product {
   private final String productName;
   private final Integer productPrice;
   private final String productComment;
   private final Integer productStock;

    public Product(String productName, Integer productPrice, String productComment, Integer productStock) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productComment = productComment;
        this.productStock = productStock;
    }

    public Integer getProductStock() {
        return productStock;
    }

    @Override
    public String toString() {
        return productName + " | " + productPrice + "원" + " | " + productComment;
    }
}
