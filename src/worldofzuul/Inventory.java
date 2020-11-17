package worldofzuul;
import java.util.ArrayList;

public class Inventory {
    // Attributes
    private ArrayList<Item> items;

    //Constructors
    Inventory(ArrayList<Item> items) {
        this.items = items;
    }
    Inventory() {
        this(new ArrayList<Item>());
    }

    //Methods
    public void addItem(Item item) {
        if (!items.contains(item)) {
            items.add(item);
        }
    }
    public void removeItem(int i) {
        items.remove(i);
    }
    public void printInventory() {
        if (items.size() == 0) {
            System.out.println("Your inventory is empty...");
        } else {
            System.out.println("List of inventory:");
            for (Item item: items) {
                System.out.println(item);
            }
        }
    }
}