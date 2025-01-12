import java.util.ArrayList;
import java.util.List;

public class Inventory {
    List<Item> items;

    public Inventory() {
        items = new ArrayList<Item>();
    }
    public Inventory(List<Item> items) {
        this.items = items;
    }
    public List<Item> getItems() {
        return items;
    }
    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void addItem(Item item) {
        items.add(item);
    }
    public void removeItem(Item item) {
        items.remove(item);
    }
    public void listItems() {
        System.out.println("#\t\tID\t\tDate\t\tDescription\t\tAmount");
        for (Item item : items) {
            System.out.println("#\t\t" + item.getId() + "\t\t" + item.getDate() + "\t" + item.getName() + "\t\t\t" + item.getPrice());
        }
    }

}
