package zaddod02.zad01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    
    public static void main(String[] args) {
        List<Square> squares = new ArrayList<>();
        int numOfSquares = 5;
        
        // utworzenie 5 obiektów Square do listy squares
        for (int i = 0; i < numOfSquares; i++) {
            int length = (int) (Math.random() * (10 - 2 + 1) + 2);
            squares.add(new Square(length));
        }
        
        System.out.println("Przed sortowaniem:");
        for (Square square : squares) {
            System.out.println(square);
        }
        
        Collections.sort(squares);
        
        System.out.println("Po sortowaniu:");
        for (Square square : squares) {
            System.out.println(square);
        }
    }
    
}
