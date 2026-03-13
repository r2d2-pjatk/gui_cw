package zad00;

public abstract class Postac {

    protected String imie;
    protected int sila;

    public Postac(String imie, int sila) {
        this.imie = imie;
        this.sila = sila;
    }

    public int wykonajAtak() {
        return 0;
    }

    public String przedstawSie() {
        return this.imie + ":" + this.sila;
    }



}
