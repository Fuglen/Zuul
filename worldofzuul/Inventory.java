package worldofzuul;
import java.util.ArrayList;
public class Inventory {
    ArrayList<Item> items;

    Inventory (ArrayList<Item> items) {
        this.items = items;
    }

    Inventory () {
        this(new ArrayList<Item>());
    }

    public void addItem (Item item) {
        if (!items.contains(item)) {
            items.add(item);
        }
    }
    public void removeItem (Item item) {
        items.remove(item);
    }

    public void printInventory () {
        System.out.println("Inventory:");
        if (items.size() == 0) {
            System.out.println("Your inventory is empty");
        } else{
        System.out.println(" - " + items);
        }
    }
}
