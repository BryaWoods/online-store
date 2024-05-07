package com.pluralsight;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
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
            System.out.println(
                    "             ░▒▓██████▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓██████▓▒░ ░▒▓██████▓▒░ ░▒▓███████▓▒░          \n" +
                    "            ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░                 \n" +
                    "            ░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░                 \n" +
                    "            ░▒▓█▓▒░      ░▒▓████████▓▒░▒▓█▓▒░▒▓█▓▒░      ░▒▓████████▓▒░░▒▓██████▓▒░           \n" +
                    "            ░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░      ░▒▓█▓▒░          \n" +
                    "            ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░      ░▒▓█▓▒░          \n" +
                    "             ░▒▓██████▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓██████▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓███████▓▒░           \n" +
                    "                                                                                              \n" +
                    "                                                                                              \n" +
                    "░▒▓███████▓▒░░▒▓████████▓▒░      ░▒▓██████████████▓▒░ ░▒▓██████▓▒░░▒▓███████▓▒░ ░▒▓██████▓▒░  \n" +
                    "░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░             ░▒▓█▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░ \n" +
                    "░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░             ░▒▓█▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░ \n" +
                    "░▒▓█▓▒░░▒▓█▓▒░▒▓██████▓▒░        ░▒▓█▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓████████▓▒░ \n" +
                    "░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░             ░▒▓█▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░ \n" +
                    "░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░             ░▒▓█▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░ \n" +
                    "░▒▓███████▓▒░░▒▓████████▓▒░      ░▒▓█▓▒░░▒▓█▓▒░░▒▓█▓▒░░▒▓██████▓▒░░▒▓███████▓▒░░▒▓█▓▒░░▒▓█▓▒░ "

                  );
            System.out.println(" ♱ ❦ 𝔅𝔦𝔢𝔫𝓋𝔢𝔫𝔦𝔡𝔬𝔰 𝔞 𝓒𝔥𝔦𝔠𝔞𝔰 𝓓𝔢 𝓜𝔬𝓭𝔞 ❦ ♱");
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
        scanner.close();
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

        inventory.sort(Comparator.comparing(Product::getName));

        System.out.println("✰ ✰ ✰ ✰ ✰ ✰ ✰ ✰ ✰ ✰ ✰ ✰ ✰ ✰ ✰ ✰ ✰ ✰ ✰ ✰ ✰ ✰ ✰ ✰ ✰ ✰ ✰ ✰ ✰");
        System.out.printf("✰%-8s | %-30s | %-10s %-10s%n",  "ID", "Name", "Price", "✰");


        for (Product product : inventory) {
            System.out.printf("✰%-8s | %-30s | %-10s %-10s%n" ,product.getId()  , product.getName() , product.getPrice(),"✰" );

        }
        System.out.println("✰ ✰ ✰ ✰ ✰ ✰ ✰ ✰ ✰ ✰ ✰ ✰ ✰ ✰ ✰ ✰ ✰ ✰ ✰ ✰ ✰ ✰ ✰ ✰ ✰ ✰ ✰ ✰ ✰");


        boolean running = true;

        while (running) {
            System.out.println(" ");
            System.out.println("1. Search for a product");
            System.out.println("2. Add a product to cart");
            System.out.println("0. Go back home");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    searchProducts(scanner);
                    break;
                case 2:
                    addToCart(scanner, inventory, cart);
                    break;
                case 0:
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
        System.out.println("Enter the ID of the product you want to add: ");
        String productId = scanner.nextLine();

        Product selectedProduct = findProductById(inventory, productId);
        if (selectedProduct != null) {
            cart.add(selectedProduct);
            System.out.println(" ");
            System.out.println(selectedProduct.getName() + " (ID: " + selectedProduct.getId() +
                    ") added to cart");
            System.out.println(" ");
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

            switch (choice) {
                case 1:
                    checkOut(cart, totalAmount, scanner);
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


    public static void searchProducts(Scanner scanner) {
        boolean running = true;
        while (running) {
            System.out.println("1) Product ID");
            System.out.println("2) Product Name");
            System.out.println("3) Products Under $20");
            System.out.println("4) Products Under $50");
            System.out.println("0) Back");


            String input = scanner.nextLine().trim();
            switch (input) {
                case "1":
                    System.out.println("Enter Product ID: ");
                    String id = scanner.nextLine().trim();
                    filterById(id, scanner);
                    break;


                case "2":
                    System.out.println("Enter Product Name: ");
                    String name = scanner.nextLine().trim();
                    filterByName(name, scanner);
                    break;


                case "3":
                    filterByUnderTwenty(scanner);
                    break;


                case "4":
                    filterByUnderFifty(scanner);
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

    public static void filterById(String id, Scanner scanner) {
        for (Product product : inventory) {
            if (product.getId().equalsIgnoreCase(id)) {
                displayProductDetails(product, scanner);
                return;
            }
        }
        System.out.println("No product found with ID: " + id);
    }

    public static void filterByName(String name, Scanner scanner) {
        for (Product product : inventory) {
            if (product.getName().equalsIgnoreCase(name)) {
                displayProductDetails(product, scanner);
                return;
            }
        }
        System.out.println("No product found with name: " + name);
    }

    private static void displayProductDetails(Product product, Scanner scanner) {
        System.out.println("ID: " + product.getId() +
                " | Name: " + product.getName() +
                " | Price: " + product.getPrice());
        System.out.println(" ");
        System.out.println("1. Add an item to cart");
        System.out.println("0. Go back");
        int addSearch = scanner.nextInt();
        scanner.nextLine();
        if (addSearch == 1) {
            addToCart(scanner, inventory, cart);
        } else if (addSearch == 0) {
            searchProducts(scanner);
        }

        System.out.println(" ");
        System.out.println("1. Add another item to cart");
        System.out.println("0. Go back");
        int addSearch2 = scanner.nextInt();
        scanner.nextLine();
        if (addSearch2 == 1) {
            addToCart(scanner, inventory, cart);
        } else if (addSearch2 == 0) {
            System.out.println("Returning to previous page.");
            System.out.println(" ");
        }
    }


    public static void filterByUnderTwenty(Scanner scanner) {
        ArrayList<Product> productsUnder20 = new ArrayList<>();
        boolean foundProducts = false;

        // Filter products under $20 and add them to the productsUnder20 list
        for (Product product : inventory) {
            if (product.getPrice() < 20) {
                productsUnder20.add(product);
            }
        }

        // Display all products under $20
        for (Product product : productsUnder20) {
            System.out.println("ID: " + product.getId() +
                    " | Name: " + product.getName() +
                    " | Price: " + product.getPrice());
        }

        // Ask the user to add products to the cart
        System.out.println("\nDo you want to add any of these products to your cart? Enter the ID of the product to add or 0 to go back.");
        String input = scanner.nextLine().trim();
        if (!input.equals("0")) {
            Product selectedProduct = findProductById(productsUnder20, input);
            if (selectedProduct != null) {
                cart.add(selectedProduct);
                System.out.println(selectedProduct.getName() + " (ID: " + selectedProduct.getId() +
                        ") added to cart");
            } else {
                System.out.println("Invalid product ID.");
            }
        } else {
            System.out.println("Returning to previous page.");
        }
    }


    public static void filterByUnderFifty(Scanner scanner) {
        ArrayList<Product> productsUnder50 = new ArrayList<>();
        boolean foundProducts = false;

        for (Product product : inventory) {
            if (product.getPrice() <= 50) {
                productsUnder50.add(product);
            }
        }


        for (Product product : productsUnder50) {
            System.out.println("ID: " + product.getId() +
                    " | Name: " + product.getName() +
                    " | Price: " + product.getPrice());
        }

        System.out.println("\nDo you want to add any of these products to your cart? Enter the ID of the product to add or 0 to go back.");
        String input = scanner.nextLine().trim();
        if (!input.equals("0")) {
            Product selectedProduct = findProductById(productsUnder50, input);
            if (selectedProduct != null) {
                cart.add(selectedProduct);
                System.out.println(selectedProduct.getName() + " (ID: " + selectedProduct.getId() +
                        ") added to cart");
            } else {
                System.out.println("Invalid product ID.");
            }
        } else {
            System.out.println("Returning to previous page.");
        }
    }


    public static void checkOut(ArrayList<Product> cart, double totalAmount, Scanner scanner) {
        System.out.println("\nYour Cart:");
        for (Product product : cart) {
            System.out.println(product.getName() + " (ID: " + product.getId() +
                    ") - Price: $" + product.getPrice());
        }
        System.out.println("Total Price: $" + totalAmount);
        System.out.println(" ");
        System.out.println("Place order?");
        System.out.println("Enter Y for yes or X to return home");
        String placeOrder = scanner.nextLine();

        if (placeOrder.equalsIgnoreCase("Y")) {
            displayReceipt(cart,totalAmount);
            createReceiptFile(cart,totalAmount);
            System.out.println("Thanks for your purchase 𝔠𝔥𝔦𝔠𝔞 ❤︎. Total amount deducted: $" + totalAmount);
            cart.clear();
            // IMPORTANT ☝︎ that will empty the cart after checkout
        } else if (placeOrder.equalsIgnoreCase("X")) {
            System.out.println("Returning to previous screen.");
        } else {
            System.out.println("Invalid input. Returning to previous screen.");
        }
    }

    private static void displayReceipt(ArrayList<Product> cart, double totalAmount){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmm");
        Date date = new Date();
        System.out.println("♱ ❦ 𝓒𝔥𝔦𝔠𝔞𝔰 𝓓𝔢 𝓜𝔬𝓭𝔞 ❦ ♱");
        System.out.println("Sales Receipt\n\n");
        System.out.println("Date and Time: " + dateFormat.format(date) + "\n");
        System.out.println("Total Amount: $" + totalAmount + "\n\n");

        System.out.println("Cart Contents:\n");
        for (Product product : cart){
            System.out.println(product.getName() + " (ID: " + product.getId() +
                    ") - Price: $" + product.getPrice() + "\n");

        }



    }


    private static void createReceiptFile(ArrayList<Product> cart, double totalAmount) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmm");
        Date date = new Date();
        String fileName = dateFormat.format(date) + ".txt";

        String directoryPath = "Receipts/";
        File directory = new File(directoryPath);

        try {

            if (!directory.exists()) {
                directory.mkdir();
            }


            File receiptFile = new File(directoryPath + fileName);
            FileWriter writer = new FileWriter(receiptFile);


            writer.write("Sales Receipt\n\n");
            writer.write("Date and Time: " + dateFormat.format(date) + "\n");
            writer.write("Total Amount: $" + totalAmount + "\n\n");


            writer.write("Cart Contents:\n");
            for (Product product : cart) {
                writer.write(product.getName() + " (ID: " + product.getId() +
                        ") - Price: $" + product.getPrice() + "\n");
            }

            // Close the writer
            writer.close();
        } catch (IOException e) {
            System.out.println("Error creating receipt file: " + e.getMessage());
        }
    }


    public static void editCart(Scanner scanner) {
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



