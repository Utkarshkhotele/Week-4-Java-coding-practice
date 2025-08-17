import java.util.*;
import java.util.stream.*;

// Product Class
class Product {
    int id;
    String name;
    double price;

    Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}

public class ProductExample {
    public static void main(String[] args) {
        // Create product list
        List<Product> products = new ArrayList<>();
        products.add(new Product(1, "Laptop", 800));
        products.add(new Product(2, "Mouse", 50));
        products.add(new Product(3, "Keyboard", 120));

        // Filter products with price > 100
        List<Product> expensive = products.stream()
                .filter(p -> p.price > 100)   // Lambda
                .collect(Collectors.toList());

        // Print result
        expensive.forEach(p -> System.out.println(p.name + " - $" + p.price));
    }
}
