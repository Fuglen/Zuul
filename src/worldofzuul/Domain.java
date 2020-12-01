package worldofzuul;

import interfaceI.DomainI;

public class Domain implements DomainI {
    //Attributes
    public Inventory inventory;

    //Constructor
    public Domain() {
        this.inventory = new Inventory();
    }

    //Methods
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