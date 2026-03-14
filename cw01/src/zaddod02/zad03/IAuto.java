package zaddod02.zad03;

public interface IAuto {
    
    default void move() {
        System.out.println("drive");
    }
    
}
