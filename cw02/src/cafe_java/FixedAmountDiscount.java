package cafe_java;

public class FixedAmountDiscount extends Discount {

    private final double amount;

    public FixedAmountDiscount(double amount) {
        super(String.format("%.2f zł zniżki", amount));
        if (amount <= 0) {
            throw new IllegalArgumentException("Kwota rabatu musi być > 0. Otrzymano: " + amount);
        } else {
            this.amount = amount;
        }
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public double apply(double originalPrice) {
        return Math.max(originalPrice - amount, 0);
    }

}
