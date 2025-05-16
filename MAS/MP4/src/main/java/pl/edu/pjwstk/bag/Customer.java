package pl.edu.pjwstk.bag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Customer {
    private String name;
    private String email;
    private List<Purchase> purchases = new ArrayList<>();

    public Customer(String name, String email) {
        setName(name);
        setEmail(email);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Customer name cannot be empty");
        }
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Customer email cannot be empty");
        }
        this.email = email;
    }

    public List<Purchase> getPurchases() {
        return Collections.unmodifiableList(purchases);
    }

    public void addPurchase(Purchase purchase) {
        if (purchase == null) {
            throw new IllegalArgumentException("Purchase cannot be null");
        }

        if (purchase.getCustomer() != this) {
            purchase.setCustomer(this);
        } else {
            purchases.add(purchase);
        }
    }

    public void removePurchase(Purchase purchase) {
        if (purchase == null) {
            throw new IllegalArgumentException("Purchase cannot be null");
        }

        if (purchases.remove(purchase)) {
            purchase.removeCustomer();
        }
    }

    @Override
    public String toString() {
        return name + " (" + email + ")";
    }
}
