package interfaceI;

import domain.*;

public interface DomainI {
    boolean processCommand(CommandWord commandWord, String secondWord);

    boolean processCommand(Command command);

    void collectItem(Command command);

    String printInventory(Inventory inventory);

    void play();

    void goRoom(Command command);

    void addItem(Item item);

    Inventory getInventory();

    void useItem(Command command, Room thisRoom);

    Room getCurrentRoom();

    void load();

    void store();


}
