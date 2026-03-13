package zad00;

import java.util.Scanner;

public
class ArenaBohaterow {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        final int MAX_DRUZYNA = 5;
        Postac[] druzyna = new Postac[MAX_DRUZYNA];
        int liczbaBohaterow = 0;

        boolean czyDziala = true;

        System.out.println("=== WITAJ W ARENIE BOHATEROW ===");

        while (czyDziala) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Zrekrutuj bohatera");
            System.out.println("2. Wyswietl druzyne");
            System.out.println("3. SYMULUJ WALKE (Polimorfizm w akcji)");
            System.out.println("4. Wyjscie");
            System.out.print("Wybierz opcje: ");

            int opcja;
            try {
                opcja = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Blad: Wprowadz liczbe!");
                continue;
            }

            switch (opcja) {
                case 1:
                    if (liczbaBohaterow >= MAX_DRUZYNA) {
                        System.out.println("Druzyna jest pelna! Nie mozesz dodac wiecej postaci.");
                    } else {
                        System.out.print("Podaj imie bohatera: ");
                        String imie = scanner.nextLine();
                        System.out.print("Podaj sile (1-20): ");
                        int sila = Integer.parseInt(scanner.nextLine());

                        System.out.println("Wybierz klase: 1. Wojownik, 2. Mag, 3. Lucznik");
                        int typ = Integer.parseInt(scanner.nextLine());

                        Postac nowaPostac = null;
                        if (typ == 1) nowaPostac = new Wojownik(imie, sila);
                        else if (typ == 2) nowaPostac = new Mag(imie, sila);
                        else if (typ == 3) nowaPostac = new Lucznik(imie, sila);
                        else System.out.println("Nieznana klasa. Bohater nie zostal dodany.");

                        if (nowaPostac != null) {
                            druzyna[liczbaBohaterow] = nowaPostac;
                            liczbaBohaterow++;
                            System.out.println("Dodano bohatera!");
                        }
                    }
                    break;

                case 2:
                    if (liczbaBohaterow == 0) {
                        System.out.println("Druzyna jest pusta.");
                    } else {
                        System.out.println("\nTWOJA DRUZYNA:");
                        for (int i = 0; i < liczbaBohaterow; i++) {
                            System.out.println((i + 1) + ". " + druzyna[i].przedstawSie());
                        }
                    }
                    break;

                case 3:
                    if (liczbaBohaterow == 0) {
                        System.out.println("Nie ma kto walczyc!");
                    } else {
                        System.out.println("\n--- ROZPOCZECIE BITWY --- ️");
                        int sumaObrazen = 0;

                        for (int i = 0; i < liczbaBohaterow; i++) {
                            sumaObrazen += druzyna[i].wykonajAtak();
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {}
                        }

                        System.out.println("-----------------------------");
                        System.out.println("Laczne obrazenia druzyny: " + sumaObrazen + " punktow!");
                        if (sumaObrazen > 100) System.out.println("POTEZNY ATAK! Przeciwnik zostal zmiazdzony!");
                        else System.out.println("Slaby atak. Przeciwnik wciaz stoi.");
                    }
                    break;

                case 4:
                    czyDziala = false;
                    System.out.println("Do widzenia!");
                    break;

                default:
                    System.out.println("Niepoprawna opcja.");
            }
        }
        scanner.close();
    }
}
