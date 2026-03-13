package cafe_java;

import java.time.LocalDateTime;

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

}
