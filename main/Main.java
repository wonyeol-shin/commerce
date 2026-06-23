package commerce.main;

import commerce.Category;
import commerce.CommerceSystem;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Category> productList = new ArrayList<>();
        CommerceSystem commerceSystem = new CommerceSystem(productList);
        commerceSystem.startProgram();

    }
}
