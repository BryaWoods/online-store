package com.pluralsight;

import java.util.ArrayList;

public class Product {

    private String id;
    private String name;
    private double price;

    public Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public static Product findProductById(ArrayList<Product> inventory, String productId) {
        for (Product product : inventory) {
            if (product.getId().equalsIgnoreCase(productId)) {
                return product; // Found the product, return it
            }
        }
        return null; // Product with specified ID not found
    }
}
