package commerce;

public class Product {
    String productName;
    Integer productPrice;
    String productComment;
    Integer productStock;

    public Product(String productName, Integer productPrice, String productComment, Integer productStock) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productComment = productComment;
        this.productStock = productStock;
    }

    @Override
    public String toString() {
        return productName + " | " + productPrice + " | " + productComment;
    }
}
