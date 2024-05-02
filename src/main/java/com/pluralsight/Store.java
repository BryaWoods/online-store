package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Store {

    public static void main(String[] args) {
        // Initialize variables
        ArrayList<Product> inventory = new ArrayList<Product>();
        ArrayList<Product> cart = new ArrayList<Product>();
        double totalAmount = 0.0;

        // ✓Load inventory from CSV file
        loadInventory(inventory);

        // ✓Create scanner to read user input
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        // ✓Display menu and get user choice until they choose to exit
        while (running) {
            System.out.println("Welcome to the Online Store!");
            System.out.println("1. Products");
            System.out.println("2. Show Cart");
            System.out.println("3. Exit");

            int input = scanner.nextInt();
            scanner.nextLine();

            // ✓Call the appropriate method based on user choice
            switch (input) {
                case 1:
                    productsMenu(scanner,inventory,cart);
                    break;
                case 2:
                    displayCart(cart, scanner, totalAmount);
                    break;
                case 3:
                    running = false;
                    System.out.println("Thank you for shopping with us!");
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
    }

    public static void loadInventory(ArrayList<Product> inventory) {
        String line;

        try {
            BufferedReader br = new BufferedReader(new FileReader("products.csv"));
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                String id = parts[0];
                String name = parts[1];
                double price = Double.parseDouble(parts[2]);

                inventory.add(new Product(id, name, price));
            }
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public static  void productsMenu(Scanner scanner, ArrayList<Product> inventory, ArrayList<Product> cart){
        boolean running = true;

        while (running) {
            System.out.println("Products");
            System.out.println("1. Search for a product");
            System.out.println("2. Add a product to cart");
            System.out.println("3. Go back home");

            int choice = scanner.nextInt();
            scanner.nextLine();

            // ✓Call the appropriate method based on user choice
            switch (choice) {
                case 1:
                    searchProducts();
                    break;
                case 2:
                    displayProducts(inventory, cart, scanner);
                    break;
                case 3:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }

    }

    public static void displayProducts(ArrayList<Product> inventory, ArrayList<Product> cart, Scanner scanner) {


        System.out.println("Products: ");
        for (Product product : inventory) {
            System.out.println(product.getId() + " - " + product.getName() + " - $" + product.getPrice());
        }

        System.out.println("\nAdd items to your cart:");
        System.out.print("Enter the ID of the product you want to add: ");
        String productId = scanner.nextLine();

        Product selectedProduct = findProductById(productId, inventory);
        if (selectedProduct != null) {
            cart.add(selectedProduct);
            System.out.println(selectedProduct.getName() + " (ID: " + selectedProduct.getId() +
                    ") added to cart");
        } else {
            System.out.println("Product not found.");
        }

        // ✓This method should display a list of products from the inventory,
        // ✓and prompt the user to add items to their cart. The method should
        // ✓prompt the user to enter the ID of the product they want to add to
        // ✓their cart. The method should
        // ✓add the selected product to the cart ArrayList.
        //question for raymond do they need the option to add another item or should it automatically return home?????
    }

    public static void displayCart(ArrayList<Product> cart, Scanner scanner, double totalAmount) {
        System.out.println("\nYour Cart:");
        for (Product product : cart) {
            totalAmount += product.getPrice();
            System.out.println(product.getName() + " (ID: " + product.getId() +
                    ") - Price: $" + product.getPrice());
        }
        System.out.println("Total Price: $" + totalAmount);
        System.out.println("To remove a product from the cart enter the product ID: ");

        String inputID = scanner.nextLine();
        for (Product product : cart) {
            if (product.getId().equalsIgnoreCase(inputID)) {
                cart.remove(product);
                System.out.println("Product has been removed.");

            }

        }

        System.out.println("Updated cart:");
        for (Product product : cart) {
            System.out.println(product);


        }
    }

        // ✓This method should display the items in the cart ArrayList, along
        // ✓with the total cost of all items in the cart. The method should
        // prompt the user to remove items from their cart by entering the ID
        // of the product they want to remove. The method should update the cart ArrayList and totalAmount
        // variable accordingly.


    public static void searchProducts(){

    }

    public static void checkOut(ArrayList<Product> cart, double totalAmount) {
        // This method should calculate the total cost of all items in the cart,
        // and display a summary of the purchase to the user. The method should
        // prompt the user to confirm the purchase, and deduct the total cost
        // from their account if they confirm.
    }

    public static Product findProductById(String id, ArrayList<Product> inventory) {
        for (Product product : inventory) {
            if (product.getId().equals(id)) {
                return product;
            }
        }

        return null;
    }
        // ✓This method should search the inventory ArrayList for a product with
        // ✓the specified ID, and return the corresponding Product object. If
        // ✓no product with the specified ID is found, the method should return
        // null.
    }
