package domain;

class Item {
    //Attributes
    private String name;

    //Constructor
    public Item(String name) {
        this.name = name;
    }

    //Methods
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "'" + name + '\'';
    }
}