package cafe_java;

public class Cafe {

    private final String name;
    private Product[] menu;
    private int menuSize;
    private Order[] orders;
    private int orderCount;

    public Cafe(String name, int menuCapacity, int orderCapacity) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("nazwa kawiarni nie może być pusta");
        }

        if (menuCapacity <= 0 || orderCapacity <= 0) {
            throw new IllegalArgumentException("pojemności muszą być większe od zera");
        }

        this.name = name;
        this.menu = new Product[menuCapacity];
        this.menuSize = 0;
        this.orders = new Order[orderCapacity];
        this.orderCount = 0;
    }

    public void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("produkt nie może być równy null");
        }

        if (menuSize == menu.length) {
            Product[] newMenu = new Product[menu.length * 2];

            for (int i = 0; i < menu.length; i++) {
                newMenu[i] = menu[i];
            }

            this.menu = newMenu;
            this.menu[menuSize++] = product;
        }
    }

    public boolean removeProduct(String productName) {
        int index = -1;

        for (int i = 0; i < menuSize; i++) {
            if (menu[i].name().equalsIgnoreCase(productName)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            return false;
        } else {
            for (int i = index; i < menuSize; i++) {
                menu[i] = menu[i + 1];
            }

            menu[menuSize--] = null;
            return true;
        }
    }

    public Product[] getProductsByCategory(String category) {
        int count = 0;

        for (Product product : menu) {
            if (product.category().equalsIgnoreCase(category)) {
                count++;
            }
        }

        Product[] filteredMenu = new Product[count];
        int idx = 0;

        for (Product product : menu) {
            if (product.category().equalsIgnoreCase(category)) {
                filteredMenu[idx++] = product;
            }
        }

        return filteredMenu;
    }
    
    public void sortMenuByPrice() {
        for (int i = 1; i < menuSize; i++) {
            Product key = menu[i];
            int j = i - 1;
            
            while (j >= 0 && menu[j].price() > key.price()) {
                menu[j + 1] = menu[j];
                j--;
            }
            
            menu[j + 1] = key;
        }
    }
    
    public void displayMenu() {
        System.out.println(name.toUpperCase());
        
        for (int i = 0; i < menuSize; i++) {
            System.out.print((i + 1) + ". ");
            System.out.println(menu[i].formatted());
        }
        
        System.out.println();
    }
    
}
