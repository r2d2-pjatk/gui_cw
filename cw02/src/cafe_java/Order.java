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
            String rightSide = String.format("%.2f zł", amount);

            int numOfSpacesToAlign = Math.max(WIDTH - (leftSide.length() + rightSide.length()), 1);
            return leftSide + " ".repeat(numOfSpacesToAlign) + rightSide;
        }

        public String generate() {
            StringBuilder sb = new StringBuilder();
            String sep1 = "=".repeat(WIDTH);
            String sep2 = "-".repeat(WIDTH);

            // nagłówek kawiarni
            sb.append(sep1 + '\n' + this.center(CAFE_NAME) + '\n' + sep1 + '\n');

            // data paragonu
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
            String dateString = formatter.format(createdAt);
            sb.append(String.format("%-12s%s\n", "Data:", dateString));

            // imię kasjera
            sb.append(String.format("%-12s%s\n", "Kasjer:", cashierName));

            // nr. zamówienia
            sb.append(String.format("%-12s#%d\n", "Zamówienie:", id));

            // klient
            sb.append(String.format("%-12s%s [%s]\n", "Klient:", customer.name(), customer.loyaltyLevel()));
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

    public static class Builder {

        private static final int INITIAL_CAPACITY = 4;

        private final int id;
        private final Customer customer;
        private OrderItem[] items;
        private int size;
        private Discount discount;

        public Builder(int id, Customer customer) {
            if (id <= 0) {
                throw new IllegalArgumentException("ID zamówienia musi być większe od zera");
            } else {
                this.id = id;
            }

            if (customer == null) {
                throw new IllegalArgumentException("klient nie może być równy null");
            } else {
                this.customer = customer;
            }

            this.items = new OrderItem[INITIAL_CAPACITY];
            this.size = 0;
        }

        private void grow() {
            OrderItem[] newItems = new OrderItem[items.length * 2];

            for (int i = 0; i < size; i++) {
                newItems[i] = items[i];
            }

            this.items = newItems;
        }

        public Builder addItem(Product product, int quantity) {
            if (size == items.length) {
                this.grow();
            }

            OrderItem item = new OrderItem(product, quantity);
            items[size++] = item;

            return this;
        }

        public Builder addItem(Product product) {
            return this.addItem(product, 1);
        }

        public Builder withDiscount(Discount discount) {
            this.discount = discount;
            return this;
        }

        public Order build() {
            if (size <= 0) {
                throw new IllegalStateException("zamówienie musi zawierać co najmniej jedną pozycję");
            }

            OrderItem[] newItems = new OrderItem[size];
            for (int i = 0; i < size; i++) {
                newItems[i] = items[i];
            }

            return new Order(id, newItems, customer, discount);
        }

    }

}
