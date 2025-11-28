package shopping;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Product {
    private String name;
    private double price;

    Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    String getName() {
        return name;
    }

    double getPrice() {
        return price;
    }
}

class CartItem {
    private Product product;
    private int quantity;

    CartItem(Product product, int quantity) {
        this.product = product;
        this.setQuantity(quantity);
    }

    Product getProduct() {
        return product;
    }

    int getQuantity() {
        return quantity;
    }

    double getTotalPrice() {
        return product.getPrice() * getQuantity();
    }

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}

class ShoppingCart {
    private List<CartItem> items = new ArrayList<>();

    void addProduct(Product product, int quantity) {
        for (CartItem item : items) {
            if (item.getProduct().getName().equals(product.getName())) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        items.add(new CartItem(product, quantity));
    }

    void removeProduct(String productName) {
        items.removeIf(item -> item.getProduct().getName().equals(productName));
    }

    double getTotal() {
        double total = 0;
        for (CartItem item : items) {
            total += item.getTotalPrice();
        }
        return total;
    }

    void showItems() {
        if (items.isEmpty()) {
            System.out.println("Cart is empty");
        } else {
            System.out.println("Items in cart:");
            for (CartItem item : items) {
                System.out.println("- " + item.getProduct().getName() + " (Qty: " + item.getQuantity() + ") : " + item.getTotalPrice());
            }
        }
    }
}

public class ShoppingApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ShoppingCart cart = new ShoppingCart();

        while (true) {
            System.out.println("\n1. Add item  2. View cart  3. Remove item  4. Checkout  5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            if (choice == 1) {
                scanner.nextLine(); // clear newline
                System.out.print("Item name: ");
                String name = scanner.nextLine();
                System.out.print("Item price: ");
                double price = scanner.nextDouble();
                System.out.print("Quantity: ");
                int quantity = scanner.nextInt();
                cart.addProduct(new Product(name, price), quantity);
                System.out.println("Added to cart.");
            } else if (choice == 2) {
                cart.showItems();
            } else if (choice == 3) {
                scanner.nextLine(); // clear newline
                System.out.print("Item name to remove: ");
                String name = scanner.nextLine();
                cart.removeProduct(name);
                System.out.println("Removed from cart.");
            } else if (choice == 4) {
                cart.showItems();
                System.out.println("Total: " + cart.getTotal());
            } else if (choice == 5) {
                break;
            } else {
                System.out.println("Invalid choice");
            }
        }

        scanner.close();
    }
}
