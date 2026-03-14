package zaddod02.zad03;

public class Amphibious implements IAuto, IBoat {
    
    private String surface;
    
    @Override
    public void move() {
        switch (surface) {
            case "land" -> IAuto.super.move();
            case "water" -> IBoat.super.move();
            default -> throw new IllegalArgumentException(
                    "Undefined or invalid surface type"
            );
        }
    }
    
}
