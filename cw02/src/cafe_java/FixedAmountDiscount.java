package cafe_java;

public class FixedAmountDiscount extends Discount {

    private final double amount;

    public FixedAmountDiscount(double amount) {
        super(String.format("%.2f zniżki", amount));
        if (amount <= 0) {
            throw new IllegalArgumentException("niepoprawna wartość rabatu: " + amount);
        } else {
            this.amount = amount;
        }
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public double apply(double originalPrice) {
        return Math.max(amount - originalPrice, 0);
    }

}
