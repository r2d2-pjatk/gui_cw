package cafe_java;

public class PercentageDiscount extends Discount {

    private final double percentage;

    public PercentageDiscount(double percentage) {
        super(String.format("%.2f%% zniżki", percentage));

        if (percentage <= 0 || percentage > 100) {
            throw new IllegalArgumentException(
                "Procent rabatu musi być w zakresie (0, 100]. Otrzymano: " + ((int) percentage) + "."
            );
        } else {
            this.percentage = percentage;
        }
    }

    public double getPercentage() {
        return percentage;
    }

    @Override
    public double apply(double originalPrice) {
        double discounted = originalPrice * (1 - percentage / 100.0);
        return Math.max(discounted, 0);
    }
}
