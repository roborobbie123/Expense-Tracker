import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Item> items;

    public Inventory() {
        items = new ArrayList<>();
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
        System.out.println("#\t\tID\t\t\tDate\t\tDescription\t\t\tAmount");
        for (Item item : items) {
            System.out.println("#\t\t" + item.getId() + "\t\t" + item.getDate() + "\t\t" + item.getName() + "\t\t\t\t$ " + item.getPrice());
        }
    }

}
