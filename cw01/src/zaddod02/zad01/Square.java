package zaddod02.zad01;

public class Square implements Comparable<Square> {

    private static int counter = 0;
    
    private int length;
    private int number;
    
    public Square(int length) {
        this.length = length;
        this.number = ++counter;
    }
    
    public int getArea() {
        return Math.powExact(length, 2);
    }
    
    @Override
    public String toString() {
        return "Square{" + "number=" + number + ", area=" + this.getArea() + '}';
    }
    
    @Override
    public int compareTo(Square other) {
        return Integer.compare(this.getArea(), other.getArea());
    }
    
}
