package interfaceI;

import domain.*;

public interface DomainI {
    boolean processCommand(CommandWord commandWord, String secondWord);

    public boolean processCommand(Command command);

    public void collectItem (Command command);

    String printInventory();

    void play();

    public void goRoom(Command command);

    public void addItem(Item item);

    public Inventory getInventory();
}
