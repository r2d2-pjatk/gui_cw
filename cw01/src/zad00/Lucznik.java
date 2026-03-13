package zad00;

import java.util.Random;

public class Lucznik extends Postac {

    private Random rand = new Random();

    public Lucznik(String imie, int sila) {
        super(imie, sila);
    }

    @Override
    public int wykonajAtak() {
        int bazoweObrazenia = this.sila;
        int calkowiteObrazenia;
        if (Math.random() < 0.5) {
            calkowiteObrazenia = bazoweObrazenia * 2;
            System.out.println("Zadano krytyczne obrażenia");
        } else {
            calkowiteObrazenia = bazoweObrazenia;
            System.out.println(this.imie + " zadał " + calkowiteObrazenia + " obrażeń");
        }

        return calkowiteObrazenia;
    }

    @Override
    public String przedstawSie() {
        return "Łucznik " + super.przedstawSie();
    }

}
