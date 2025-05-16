package pl.edu.pjwstk.bag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Product {
    private String name;
    private double price;
    private List<Purchase> purchases = new ArrayList<>();

    public Product(String name, double price) {
        setName(name);
        setPrice(price);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Product name cannot be empty");
        }
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Product price cannot be negative");
        }
        this.price = price;
    }

    public List<Purchase> getPurchases() {
        return Collections.unmodifiableList(purchases);
    }

    public void addPurchase(Purchase purchase) {
        if (purchase == null) {
            throw new IllegalArgumentException("Purchase cannot be null");
        }

        if (purchase.getProduct() != this) {
            purchase.setProduct(this);
        } else {
            purchases.add(purchase);
        }
    }

    public void removePurchase(Purchase purchase) {
        if (purchase == null) {
            throw new IllegalArgumentException("Purchase cannot be null");
        }

        if (purchases.remove(purchase)) {
            purchase.removeProduct();
        }
    }

    @Override
    public String toString() {
        return name + " ($" + price + ")";
    }
}
