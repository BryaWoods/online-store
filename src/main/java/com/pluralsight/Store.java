package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import static com.pluralsight.Product.findProductById;

public class Store {
    private static ArrayList<Product> inventory = new ArrayList<Product>();
    private static ArrayList<Product> cart = new ArrayList<Product>();

    public static void main(String[] args) {
        double totalAmount = 0.0;

        loadInventory("products.csv");

        Scanner scanner = new Scanner(System.in);

        boolean running = true;

        while (running) {
            System.out.println("Welcome to the Online Store!");
            System.out.println("1. Products");
            System.out.println("2. Show Cart");
            System.out.println("3. Exit");

            int input = scanner.nextInt();
            scanner.nextLine();


            switch (input) {
                case 1:
                    displayProducts(inventory, cart, scanner);
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

    public static void loadInventory(String filename) {
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


    public static void displayProducts(ArrayList<Product> inventory, ArrayList<Product> cart, Scanner scanner) {


        System.out.println("Products: ");
        for (Product product : inventory) {
            System.out.println(product.getId() + " - " + product.getName() + " - $" + product.getPrice());
        }

        boolean running = true;

        while (running) {
            System.out.println(" ");
            System.out.println("1. Search for a product");
            System.out.println("2. Add a product to cart");
            System.out.println("3. Go back home");

            int choice = scanner.nextInt();
            scanner.nextLine();

            // ✓Call the appropriate method based on user choice
            switch (choice) {
                case 1:
                    searchProducts(scanner);
                    break;
                case 2:
                    addToCart(scanner, inventory, cart);
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

    public static void addToCart(Scanner scanner, ArrayList<Product> inventory, ArrayList<Product> cart) {

        System.out.println("\nAdd items to your cart:");
        System.out.print("Enter the ID of the product you want to add: ");
        String productId = scanner.nextLine();

        Product selectedProduct = findProductById(inventory, productId);
        if (selectedProduct != null) {
            cart.add(selectedProduct);
            System.out.println(selectedProduct.getName() + " (ID: " + selectedProduct.getId() +
                    ") added to cart");
        } else {
            System.out.println("Product not found.");
        }

    }

    public static void displayCart(ArrayList<Product> cart, Scanner scanner, double totalAmount) {
        System.out.println("\nYour Cart:");
        for (Product product : cart) {
            totalAmount += product.getPrice();
            System.out.println(product.getName() + " (ID: " + product.getId() +
                    ") - Price: $" + product.getPrice());
        }
        System.out.println("Total Price: $" + totalAmount);
        System.out.println(" ");

        boolean running = true;

        while (running) {
            System.out.println(" ");
            System.out.println("1. Check Out");
            System.out.println("2. Edit Cart");
            System.out.println("3. Continue Shopping ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            // ✓Call the appropriate method based on user choice
            switch (choice) {
                case 1:
                    //checkOut();
                    break;
                case 2:
                    editCart(scanner);
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

    // ✓This method should display the items in the cart ArrayList, along
    // ✓with the total cost of all items in the cart. The method should
    // prompt the user to remove items from their cart by entering the ID
    // of the product they want to remove. The method should update the cart ArrayList and totalAmount
    // variable accordingly.


    public static void searchProducts(Scanner scanner) {
        boolean running = true;
        while (running) {
            System.out.println("1) Product ID");
            System.out.println("2) Product Name");
            System.out.println("3) Products Under $10");
            System.out.println("4) Products Under $20");
            System.out.println("0) Back");


            String input = scanner.nextLine().trim();
            switch (input) {
                case "1":
                    System.out.println("Enter Product ID: ");
                    String id = scanner.nextLine().trim();
                    filterById(id);
                    break;


                case "2":
                    System.out.println("Enter Product Name: ");
                    String name = scanner.nextLine().trim();
                    filterByName(name);
                    break;


                case "3":
                    filterByUnderTen();
                    break;


                case "4":
                    filterByUnderTwenty();
                    break;

                case "0":
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option");
                    break;
            }
        }


    }

    public static void filterById(String id) {
        boolean foundProducts = false;
        for (Product product : inventory) {
            if (product.getId().equalsIgnoreCase(id)) {
                System.out.println("ID: " + product.getId() +
                        " | Name: " + product.getName() +
                        " | Price: " + product.getPrice());

                foundProducts = true;
            }
        }

        if (!foundProducts) {
            System.out.println("No transactions found in the specified date range.");
        }
    }

    public static void filterByName(String name) {
        boolean foundProducts = false;
        for (Product product : inventory) {
            if (product.getName().equalsIgnoreCase(name)) {
                System.out.println("ID: " + product.getId() +
                        " | Name: " + product.getName() +
                        " | Price: " + product.getPrice());

                foundProducts = true;
            }
        }

        if (!foundProducts) {
            System.out.println("No transactions found in the specified date range.");
        }
    }

    public static void filterByUnderTen() {
        boolean foundProducts = false;
        for (Product product : inventory) {
            if (product.getPrice() <= 10) {
                System.out.println("ID: " + product.getId() +
                        " | Name: " + product.getName() +
                        " | Price: " + product.getPrice());

                foundProducts = true;
            }
        }
        if (!foundProducts) {
            System.out.println("No products found in the specified price range.");
        }
    }


    public static void filterByUnderTwenty() {
        boolean foundProducts = false;
        for (Product product : inventory) {
            if (product.getPrice() <= 20) {
                System.out.println("ID: " + product.getId() +
                        " | Name: " + product.getName() +
                        " | Price: " + product.getPrice());

                foundProducts = true;
            }
        }
        if (!foundProducts) {
            System.out.println("No products found in the specified price range.");
        }
    }


    public static void checkOut(ArrayList<Product> cart, double totalAmount) {
        // This method should calculate the total cost of all items in the cart,
        // and display a summary of the purchase to the user. The method should
        // prompt the user to confirm the purchase, and deduct the total cost
        // from their account if they confirm.
    }

    public static void editCart(Scanner scanner){
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

}

