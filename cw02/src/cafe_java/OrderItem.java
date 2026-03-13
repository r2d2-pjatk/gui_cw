package cafe_java;

public record OrderItem(Product product, int quantity) {

    public OrderItem {
        if (product == null) {
            throw new IllegalArgumentException("produkt nie może być pusty");
        }

        if (quantity < 1) {
            throw new IllegalArgumentException("Ilość musi wynosić co namniej 1. Otrzymano " + quantity);
        }

    }

    public double totalPrice() {
        return product.price() * quantity;
    }

    public String formatted() {
        String leftSide = String.format("%d x %s", quantity, product.name());
        String rightSide = String.format("%.2f %s", this.totalPrice(), "zł");

        int expectedRowLength = 40;
        String spaces = " ".repeat(40 - leftSide.length() - rightSide.length());

        return leftSide + spaces + rightSide;
    }
}
