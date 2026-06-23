package commerce;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private final String category;
    private final List<Product> productList;


    public Category(String categoryName) {
        this.productList = new ArrayList<>();
        this.category = categoryName;
    }

    public void removeProduct(Product product) {
        productList.remove(product);
    }


    public void addProduct(Product product) {
        productList.add(product);
    }

    @Override
    public String toString() {
        return category;
    }

    public String getCategory() {
        return category;
    }

    public List<Product> getProductList() {
        return new ArrayList<>(productList);
    }

    public Product getProduct(String name) {

        for (Product product : productList) {
            if (name.equals(product.getProductName())) {
                return product;
            }
        }

        return null;
    }
}
