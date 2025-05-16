package pl.edu.pjwstk.dynamic;

public class Product {
    private String name;
    private double price;

    private static final double MAX_PRICE_INCREASE_PERCENT = 20.0;

    public Product(String name, double price) {
        setName(name);
        setPrice(price);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double newPrice) {
        if (this.price == 0 || newPrice <= this.price) {
            this.price = newPrice;
            return;
        }

        double increasePercent = ((newPrice - this.price) / this.price) * 100;
        if (increasePercent > MAX_PRICE_INCREASE_PERCENT) {
            throw new IllegalArgumentException(
                    "Price increase too high! Maximum allowed increase is " +
                            MAX_PRICE_INCREASE_PERCENT + "%. Attempted increase: " +
                            String.format("%.2f", increasePercent) + "%");
        }

        this.price = newPrice;
    }
}
