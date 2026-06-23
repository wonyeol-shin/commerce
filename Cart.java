package commerce;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private final List<Product> cart = new ArrayList<>();

    public void printCart() {
        for (Product p : cart) {
            System.out.println(p + " | 수량: " + p.getProductStock() + "개");
        }
    }

    public List<Product> getProductInCart() {
        return  new ArrayList<>(cart);
    }

    public void addCart(Product product) {
        Integer index = findProductIndex(product);
        // 동일 상품이 이미 장바구니에 있다면 수량 1 증가
        if ( index  >= 0 )  {
            cart.get(index).addStock();
            return;
        }

        // 동일 상품이 없을 경우 추가
        cart.add( new Product(product.getProductName(), product.getProductPrice(), product.getProductComment(), 1));

    }

    private Integer findProductIndex(Product product) {

        for (int i = 0; i < cart.size(); i++) {
            if (product.getProductName().equals(cart.get(i).getProductName())) {
                return i;
            }

        }
        return -1;
    }

    public Integer totalPrice() {
        Integer sum = 0;
        for (Product product : cart) {
            sum += (product.getProductPrice() * product.getProductStock());
        }
        return sum;
    }

}
