package commerce;

import java.util.List;
import java.util.Scanner;

public class CommerceSystem {
    private final List<Category> categories;

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
            for (int i = 0; i < categories.size(); i++) {
                System.out.println( (i + 1)  + "." + categories.get(i) );
            }
            System.out.println("0. 종료      | 프로그램 종료");

            choice = sc.nextLine();
            // 0 입력 시 종로
            if (choice.equals("0")) {
                break;
            }
            // 입력 값이 정수가 아닌경우
            try {
                Integer.parseInt(choice);
            } catch (RuntimeException r) {
                System.out.println("잘못된 값 입력");
                continue;
            }

            // 없는 인덱스 범위라면
            try {
                 categories.get(Integer.parseInt(choice) -1);
            } catch (IndexOutOfBoundsException index) {
                System.out.println("없는 카테고리 입니다.");
                continue;
            }

//            // 입력한 인덱스의 카테고리 객체를 추출
            Category selectedCategory = categories.get(Integer.parseInt(choice) -1);

            // 입력받은 인덱스의 카테고리 이름과 해당 카테고리가 가지고 있는 상품들 추출
            System.out.println(selectedCategory.getCategory() + "카테고리");
            List<Product> products = selectedCategory.getProductList();

            for (int i = 0; i < products.size(); i++) {
                System.out.println( (i+1) + ". " + products.get(i));
            }

            System.out.println("0. 뒤로가기");
            choice = sc.nextLine();

            if (choice.equals("0")) {
                continue;
            }

            // 입력 값이 정수가 아닌경우
            try {
                Integer.parseInt(choice);
            } catch (RuntimeException r) {
                System.out.println("잘못된 값 입력");
                continue;
            }

            try {
                products.get(Integer.parseInt(choice) -1 );
            } catch (IndexOutOfBoundsException u) {
                System.out.println("없는 상품입니다.");
                continue;
            }

            Product product = products.get(Integer.parseInt(choice) -1);

            System.out.println("선택한 상품: " + product + " | " + "재고: " + product.getProductStock() + "개");

        }


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
