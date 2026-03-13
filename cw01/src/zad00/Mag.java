package zad00;

import java.util.Random;

public class Mag extends Postac {

    private Random rand = new Random();

    public Mag(String imie, int sila) {
        super(imie, sila);
    }

    @Override
    public int wykonajAtak() {
        int bazoweObrazenia = 20;
        int losowyBonus = rand.nextInt(0, 14);
        int calkowiteObrazenia = bazoweObrazenia + losowyBonus + this.sila / 2;

        System.out.println(this.imie + " zadał " + calkowiteObrazenia + " obrażeń");

        return calkowiteObrazenia;
    }

    @Override
    public String przedstawSie() {
        return "Mag " + super.przedstawSie();
    }

}
