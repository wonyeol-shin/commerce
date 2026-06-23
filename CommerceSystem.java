package commerce;

import java.util.List;
import java.util.Scanner;

public class CommerceSystem {
    private final List<Category> categories;
    private Cart cart;

    public CommerceSystem(List<Category> categories) {
        this.categories = categories;
    }

    // 카테고리에 일치하는 상품 등록요청
    private void addProduct( Category category , Product[] products) {
        for (Product product : products) {
            category.addProduct(product);
        }
    }

    // 카테고리 이름을 입력받고 새로운 카테고리 생성 및 리스트에 추가
    private Category addCategory(String categoryName) {
        Category category = new Category(categoryName);
        categories.add(category);
        return category;
    }

    public void startProgram() {
        commerceContext();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.flush();
            System.out.println("[ 실시간 커머스 플랫폼 메인 ]");
            String choice;
            Integer indexNum;

            for (int i = 0; i < categories.size(); i++) {
                System.out.println( (i + 1)  + "." + categories.get(i) );
            }
            System.out.println("0. 종료      | 프로그램 종료");

            // 고객 장바구니가 비어있지 않을 경우
            if (!isCartNull(cart)) {
                System.out.println();
                System.out.println("[ 주문 관리 ]");
                System.out.println(categories.size()+1 + ". 장바구니 확인    | 장바구니를 확인 후 주문합니다.");
                System.out.println( (categories.size()+2) + ". 주문 취소       | 진행중인 주문을 취소합니다."  );

            }

            choice = sc.nextLine();

            if (isNotNum(choice)) {
                continue;
            }

            // 0 입력 시 종로
            if (choice.equals("0")) {
                break;
            }

            indexNum = Integer.parseInt(choice) -1;

            // 장바구니 관리일 경우
            if ((indexNum ) == categories.size() && !isCartNull(cart) ) {
                System.out.println("아래와 같이 주문 하시겠습니까?");
                System.out.println();
                System.out.println("[ 장바구니 내역 ]");
                List<Product> cartProducts = cart.getProductInCart();
                for (Product cartProduct : cartProducts) {
                    System.out.println(cartProduct + " | " + "수량: " + cartProduct.getProductStock() + "개");
                }
                System.out.println();
                System.out.println("[ 총 주문 금액 ]");
                System.out.println(cart.totalPrice() + "원");
                System.out.println();
                System.out.println("1. 주문 확정      2. 메인으로 돌아가기");
                choice = sc.nextLine();
                if (isNotNum(choice)) {
                    continue;
                }
                // 주문 확정

                // 1. 전체 장바구니를 순회한다.
                // 2. 장바구니의 있는 상품의 이름을 가져온다.
                // 3. 상품이름으로 카데고리의 상품을 가져온다.
                // 4. 장바구니의 상품 재고와 카데고리 상품 재고 크기를 비교한다.
                // 5. 하나다로 재고가 부족할 경우 주문 취소를 한다.
                // 6. 재고가 부족하지 않을 경우 카케고리 재고를 깎는다.
                if (choice.equals("1")) {
                    if (isLessThanCart(cartProducts)) {
                        continue;
                    }

                    startOrder(cartProducts);
                    System.out.println("주문이 완료되었습니다! 총 금액 : " +  cart.totalPrice());
                    cart = null;
                    continue;
                }
                //주문 취소
            } else if ((indexNum) == (categories.size() + 1) && !isCartNull(cart) ) {
                cart = null;
                System.out.println("주문 취소 완료");
                continue;
            }


            // 없는 인덱스 범위라면
            if (isNotIndexBound(indexNum, categories)) {
                continue;
            }

//           // 입력한 인덱스의 카테고리 객체를 추출
            Category selectedCategory = categories.get(indexNum);

            // 입력받은 인덱스의 카테고리 이름과 해당 카테고리가 가지고 있는 상품들 추출
            System.out.println(selectedCategory.getCategory() + " 카테고리");
            List<Product> categoryProducts = selectedCategory.getProductList();

            for (int i = 0; i < categoryProducts.size(); i++) {
                System.out.println( (i+1) + ". " + categoryProducts.get(i));
            }

            System.out.println("0. 뒤로가기");
            choice = sc.nextLine();

            if (isNotNum(choice)) {
                continue;
            }
            // 0 입력시 처음 부분으로 복귀
            if (choice.equals("0")) {
                continue;
            }

            indexNum = Integer.parseInt(choice) -1;
            // 없는 상품 인덱스라면
            if (isNotProductIndexBound(indexNum, categoryProducts)) {
                continue;
            }

            Product categoryProduct = categoryProducts.get(indexNum);
            System.out.println("선택한 상품: " + categoryProduct + " | " + "재고: " + categoryProduct.getProductStock() + "개");
            System.out.println("위 상품을 장바구니에 추가하시겠습니까?");
            System.out.println("1. 확인        2. 취소");
            choice = sc.nextLine();

            if (isNotNum(choice)) {
                continue;
            }

            if (choice.equals("1")) {
                // 고객의 장바구니가 없을 경우 추가
                if (isCartNull(cart)) {
                    cart = new Cart();
                }
                cart.addCart(categoryProduct);
                cart.printCart();
            }



        }
    }

    private void startOrder(List<Product> cartProducts) {
        for (Product cartProduct : cartProducts) {
            String name = cartProduct.getProductName();
            Integer quantity = cartProduct.getProductStock();

            for (Category category : categories) {
                Product product = category.getProduct(name);
                if (product != null) {
                    Integer previousQuantity = product.getProductStock();
                    product.reduceStock(quantity);
                    Integer currentQuantity = product.getProductStock();

                    System.out.println(name + "재고가 " + previousQuantity + "개 -> " + currentQuantity +"개로 업데이트 되었습니다.");
                }
            }

        }

    }


    // 주문 시 카트에 있는 상품의 수량이 실제 재고보다 많이 담겼는지 검증
    private boolean isLessThanCart(List<Product> cartProducts) {
        for (Product cartProduct : cartProducts) {
            String name = cartProduct.getProductName();
            Integer quantity = cartProduct.getProductStock();

            for (Category category : categories) {
                Product product = category.getProduct(name);
                if (product != null) {
                    if (quantity > product.getProductStock()) {
                        System.out.println("재고 부족");
                        return true;
                    }
                }

            }

        }

        return false;
    }

    private boolean isCartNull(Cart cart) {
        if (cart == null) {
            return true;
        }
        return false;
    }

    private boolean isNotProductIndexBound(Integer choice, List<Product> products) {
        try {
            products.get(choice);
        } catch (IndexOutOfBoundsException index) {
            System.out.println("없는 상품입니다.");
            return  true;
        }
        return false;
    }

    private boolean isNotIndexBound(Integer choice, List<Category> categories) {
        try {
            categories.get(choice);
        } catch (IndexOutOfBoundsException index) {
            System.out.println("없는 카테고리 입니다.");
            return  true;
        }
        return false;
    }

    private boolean isNotNum(String choice) {
        try {
            Integer.parseInt(choice);
        } catch (RuntimeException r) {
            System.out.println("잘못된 값 입력");
            return true;
        }
        return false;
    }

    // 코드가 너무 길어지고 추후 기능 추가를 위해 카테고리와 상품 추가 분리
    private void commerceContext() {
        Category electronic =  addCategory("전자제품");
        Product galaxyS25 = new Product("Galaxy S25", 1_200_000, "최신 안드로이드 스마트폰", 5);
        Product iphone16 = new Product("iPhone 16", 1_350_000, "Apple의 최신 스마트폰", 1);
        Product macbookProM3 = new Product("MacBook Pro", 2_400_000, "M3 칩셋이 탑재된 노트북", 2);
        Product airpodPro1 = new Product("AirPods Pro1", 350_000, "노이즈 캔슬링 무선 이어폰", 2);
        Product[] electronics = {galaxyS25, iphone16, macbookProM3, airpodPro1};
        addProduct(electronic, electronics);

        Category cloth =  addCategory("의류");
        Product pant = new Product("N pant", 250_000, "일본산 바지", 5);
        Product[] clothes = {pant};
        addProduct(cloth, clothes);

        Category food = addCategory("식품");
        Product mandu = new Product("비비고 만두", 8_000, "비비고 군만두", 30);
        Product chicken = new Product("용가리 치킨", 5_500, "하림 용가리 치킨", 10);
        Product[] foods = {mandu, chicken};
        addProduct(food, foods);

    }

}
