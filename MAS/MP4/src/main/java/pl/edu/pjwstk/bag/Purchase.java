package pl.edu.pjwstk.bag;

import java.time.LocalDateTime;

public class Purchase {
    private Customer customer;
    private Product product;
    private int quantity;
    private LocalDateTime purchaseTime;

    public Purchase(Customer customer, Product product, int quantity) {
        setCustomer(customer);
        setProduct(product);
        setQuantity(quantity);
        this.purchaseTime = LocalDateTime.now();
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null");
        }

        if (this.customer == customer) {
            return;
        }

        if (this.customer != null) {
            Customer oldCustomer = this.customer;
            this.customer = null;
            oldCustomer.removePurchase(this);
        }

        this.customer = customer;
        customer.addPurchase(this);
    }

    public void removeCustomer() {
        this.customer = null;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }

        if (this.product == product) {
            return;
        }

        if (this.product != null) {
            Product oldProduct = this.product;
            this.product = null;
            oldProduct.removePurchase(this);
        }

        this.product = product;
        product.addPurchase(this);
    }

    public void removeProduct() {
        this.product = null;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Purchase quantity must be positive");
        }
        this.quantity = quantity;
    }

    public LocalDateTime getPurchaseTime() {
        return purchaseTime;
    }

    @Override
    public String toString() {
        return customer + " purchased " + quantity + " of " + product + " at " + purchaseTime;
    }
}
