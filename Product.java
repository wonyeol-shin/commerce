package commerce;

public class Product {
   private  String productName;
   private  Integer productPrice;
   private  String productComment;
   private Integer productStock;

    public Product(String productName, Integer productPrice, String productComment, Integer productStock) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productComment = productComment;
        this.productStock = productStock;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }

    public void setProductComment(String productComment) {
        this.productComment = productComment;
    }

    public void setProductStock(Integer productStock) {
        this.productStock = productStock;
    }

    public boolean addStock() {
        this.productStock++;
        return true;
    }

    public boolean reduceStock() {
        if (this.productStock == 0) {
            System.out.println("재고 부족");
            return false;
        }
        this.productStock--;
        return true;
    }

    public boolean reduceStock(Integer stock) {
        if (this.productStock < stock) {
            System.out.println("재고 부족");
            return false;
        }
        this.productStock-=stock;
        return true;
    }

    public Integer getProductStock() {
        return productStock;
    }

    public String getProductName() {
        return productName;
    }

    public Integer getProductPrice() {
        return productPrice;
    }

    public String getProductComment() {
        return productComment;
    }

    @Override
    public String toString() {
        return productName + " | " + productPrice + "원" + " | " + productComment;
    }



}
