package domain;

import interfaceI.DomainI;

public class Domain implements DomainI {
    //Attributes
    public Inventory inventory;
    public String filename;

    //Constructor
    public Domain(String filename) {
        this.inventory = new Inventory();
        this.filename = filename;
    }

    //Methods
    @Override
    public void load() {
        inventory.load(filename);
    }
    @Override
    public void store() {
        inventory.store(filename);
    }
    @Override
    public void addItem(String name) {
        Item item = new Item(name);
        inventory.addItem(item);
    }
    @Override
    public void removeItem(int i) {
        inventory.removeItem(i);
    }
    @Override
    public void printInventory() {
        inventory.printInventory();
    }
}