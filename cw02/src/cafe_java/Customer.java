package cafe_java;

public record Customer(String name, String email, int loyaltyPoints) {

    public Customer {

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Imię klienta nie może być puste");
        }

        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Nieprawidłowy adres email: " + email);
        }

        if (loyaltyPoints < 0) {
            throw new IllegalArgumentException("Liczba punktów nie może być ujemna");
        }
    }

    public String loyaltyLevel() {
        if (loyaltyPoints >= 200) {
            return "ZŁOTY";
        } else if (loyaltyPoints >= 100) {
            return "SREBRNY";
        } else if (loyaltyPoints >= 50) {
            return "BRĄZOWY";
        } else {
            return "STANDARD";
        }
    }

    public String formatted() {
        return String.format("imię: %s, liczba punktów: %d, poziom lojalności: %s", name, loyaltyPoints, this.loyaltyLevel());
    }

}
