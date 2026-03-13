package zad00;

public class Wojownik extends Postac {

    public Wojownik(String imie, int sila) {
        super(imie, sila);
    }

    @Override
    public int wykonajAtak() {
        int wartoscObrazen = (int) (this.sila * 1.5);
        System.out.println(this.imie + " zadał " + wartoscObrazen + " obrażeń");
        return wartoscObrazen;
    }

    @Override
    public String przedstawSie() {
        return "Wojownik " + super.przedstawSie();
    }

}
