package cafe_java;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Order {

    private int id;
    private final OrderItem[] items;
    private final Customer customer;
    private final LocalDateTime createdAt;
    private Discount discount;

    private Order(int id, OrderItem[] items, Customer customer, Discount discount) {
        this.id = id;

        OrderItem[] newItems = new OrderItem[items.length];
        for (int i = 0; i < items.length; i++) {
            newItems[i] = items[i];
        }

        this.items = newItems;
        this.customer = customer;
        this.discount = discount;
        this.createdAt = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Discount getDiscount() {
        return discount;
    }

    public OrderItem[] getItems() {
        OrderItem[] newItems = new OrderItem[items.length];

        for (int i = 0; i < items.length; i++) {
            newItems[i] = items[i];
        }

        return newItems;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public int getLineCount() {
        return items.length;
    }

    public int getItemCount() {
        int count = 0;

        for (OrderItem item : items) {
            count += item.quantity();
        }

        return count;
    }

    public double calculateSubtotal() {
        double sum = 0;

        for (OrderItem item : items) {
            sum += item.totalPrice();
        }

        return sum;
    }

    public double calculateTotal() {
        double subtotal = this.calculateSubtotal();

        if (discount != null) {
            return discount.apply(subtotal);
        } else {
            return subtotal;
        }
    }

    @Override
    public String toString() {
        return String.format("%d, %s, %d, %.2f", id, customer.name(), this.getItemCount(), this.calculateTotal());
    }

    public class Receipt {

        private static final String CAFE_NAME = "KAWIARNIA \"POD JAVĄ\"";
        private static final int WIDTH = 42;

        private final String cashierName;

        public Receipt(String cashierName) {
            if (cashierName == null || cashierName.isBlank()) {
                throw new IllegalArgumentException("imię kasjera nie może być puste");
            } else {
                this.cashierName = cashierName;
            }
        }

        private String center(String text) {
            int leadingSpacesLength = Math.max((WIDTH - text.length()) / 2, 0);
            return " ".repeat(leadingSpacesLength) + text;
        }

        private String formatLine(String label, double amount) {
            String leftSide = '\t' + label;
            String rightSide = String.format("%.2f zł", amount);

            int numOfSpacesToAlign = Math.max(WIDTH - (leftSide.length() + rightSide.length()), 1);
            return leftSide + " ".repeat(numOfSpacesToAlign) + rightSide;
        }

        private String formatNegLine(String label, double amount) {
            String leftSide = '\t' + label;
            String rightSide = String.format("-%.2f zł", amount);

            int numOfSpacesToAlign = Math.max(WIDTH - (leftSide.length() + rightSide.length()), 1);
            return leftSide + " ".repeat(numOfSpacesToAlign) + rightSide;
        }

        private String generate() {
            StringBuilder sb = new StringBuilder();
            String sep1 = "=".repeat(WIDTH);
            String sep2 = "-".repeat(WIDTH);

            sb.append(sep1 + '\n' + this.center(CAFE_NAME) + '\n' + sep1 + '\n');

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
            sb.append(formatter.format(createdAt));

            sb.append('\n' + cashierName + '\n');

            sb.append(id + '\n');

            sb.append(customer.name() + '\n');
            sb.append(customer.name() + ' ' + customer.loyaltyLevel() + '\n');
            sb.append("\n");

            for (OrderItem item : items) {
                sb.append(item.formatted() + '\n');
            }

            sb.append('\n');
            sb.append(sep2 + "\n");

            double subtotal = calculateSubtotal();
            sb.append(formatLine("Suma:", subtotal) + '\n');

            if (discount != null) {
                double savings = discount.savings(subtotal);
                sb.append(
                        formatNegLine("Rabat: " + discount.getDescription(), savings) + '\n'
                );
            }

            sb.append(sep2 + '\n');
            sb.append(formatLine("DO ZAPŁATY:", calculateTotal()) + '\n');

            sb.append(sep1 + '\n' + this.center("Dziękujemy!") + '\n' + sep1 + '\n');

            return sb.toString();
        }

    }

}
