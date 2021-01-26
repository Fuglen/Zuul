package domain;
import data.FileBackend;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    // Attributes
    private ArrayList<Item> items;

    //Constructors
    public Inventory(ArrayList<Item> items) {
        this.items = items;
    }
    public Inventory() {
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

    public String printInventory() {
        if (items.size() == 0) {
            System.out.println("Your inventory is empty...");
        } else {
            System.out.println("List of inventory:");
            for (Item item: items) {
                System.out.println(item);
            }
        }
        return null;
    }

    public boolean load (String filename) {
        FileBackend fb = new FileBackend(filename);
        if (fb==null) return false;

        List<String> entries = fb.load();
        if (entries==null) return false;

        for (String entry: entries) {
            Item item = new Item(entry);
            if (item!=null) addItem(item);
        }

        return true;
    }

    public boolean store (String filename) {
        FileBackend fb = new FileBackend(filename);
        if (fb==null) return false;

        List<String> entries = new ArrayList<String>();
        for (Item item: items) {
            entries.add(item.getName());
        }

        return fb.store(entries);
    }
}