package zaddod01;

public class Hero {
    
    private String name;
    private int age;
    
    public Hero(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void sayHelloTo(Hero other) {
        System.out.println("Hi " + other.name + "!");
    }
    
}
