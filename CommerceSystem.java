package commerce;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CommerceSystem {
    private final List<Product> productList;

    public CommerceSystem(List<Product> productList) {
        this.productList = productList;
    }

    public void addProduct(Product product) {
        productList.add(product);
    }

    public void startProgram() {
        System.out.println("[ 실시간 커머스 플랫폼 - 전자제품 ]");

        Product galaxyS25 = new Product("Galaxy S25", 1_200_000, "최신 안드로이드 스마트폰", 5);
        Product iphone16 = new Product("iPhone 16", 1_350_000, "Apple의 최신 스마트폰", 1);
        Product macbookProM3 = new Product("MacBook Pro", 2_400_000, "M3 칩셋이 탑재된 노트북", 2);
        Product airpodPro1 = new Product("AirPods Pro1", 350_000, "노이즈 캔슬링 무선 이어폰", 2);
        Product[] products = {galaxyS25, iphone16, macbookProM3, airpodPro1};

        for (Product product : products) {
            addProduct(product);
        }

        Scanner sc = new Scanner(System.in);

        while (true) {
            String choice;

            for (int i = 0; i < productList.size(); i++) {
                System.out.println(  (i+1) + ". " +  productList.get(i));
            }
            System.out.println("0. 종료  | 프로그램 종료");
            choice = sc.nextLine();

            if (choice.equals("0")) {
                System.out.println("커머스 플랫폼을 종료합니다.");
                break;
            }

        }



    }

}
