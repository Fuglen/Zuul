package interfaceI;

import domain.*;

public interface DomainI {
    boolean processCommand(CommandWord commandWord, String secondWord);

    boolean processCommand(Command command);

    void collectItem(Command command);

    //public void dropItem();

    String printInventory(Inventory inventory);

    void play();

    void goRoom(Command command);

    void addItem(Item item);

    Inventory getInventory();

    void useItem(Command command, Room thisRoom);

<<<<<<< HEAD
    Room getCurrentRoom();

    void load();
=======

    //public void dropItem();
>>>>>>> a2feb6b8261ffaf2dcbdb2a2a64b37b97e2439de

    void store();


}
