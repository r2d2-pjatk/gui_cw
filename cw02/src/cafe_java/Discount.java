package cafe_java;

public abstract class Discount {

    private final String description;

    public Discount(String description) {
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Opis rabatu nie jest pusty");
        } else {
            this.description = description;
        }
    }

    public String getDescription() {
        return description;
    }

    public abstract double apply(double originalPrice);

    public double savings(double originalPrice) {
        double priceAfterDiscount = this.apply(originalPrice);
        return priceAfterDiscount - originalPrice;
    }

    @Override
    public String toString() {
        return "Rabat: " + description;
    }
}
