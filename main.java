import java.util.*;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Inventory inventory = new Inventory();
        Item item1 = new Item("food", 35, 1);
        Item item2 = new Item("drinks", 50, 2);
        inventory.addItem(item1);
        inventory.addItem(item2);



        while(true) {
            System.out.println("Enter command or 'q' to save and quit");
            String input = scanner.nextLine();

            if(input.equals("q")) {
                System.out.println("Exiting and saving...");
                break;
            }
            if(input.isEmpty()) {
                System.out.println("Invalid input. Please try again.");
            }


            String[] commands = input.split(" ");
            System.out.println(Arrays.toString(commands));
            // list command
            if(commands[0].equals("list") && commands.length == 1) {
                inventory.listItems();
            }

            // delete command
            if(commands[0].equals("delete") && commands.length == 2) {
                String id = commands[1];
                for(Item item : inventory.getItems()) {
                    if(id.equals(String.valueOf(item.getId()))) {
                        inventory.removeItem(item);
                        System.out.println("Item deleted.");
                        break;
                    }
                }
            }

            // overall summary command
            if(commands[0].equals("summary") && commands.length == 1) {
                int sum = 0;
                for(Item item : inventory.getItems()) {
                    sum += item.getPrice();
                }
                System.out.println("Total expenses: " + sum);
            }
            // date specific summary command
            if(commands[0].equals("summary") && commands.length == 3) {
                int sum = 0;
                if(Objects.equals(commands[1], "month")) {
                    if(commands[2].length() == 1) {
                        commands[2] = '0' + commands[2];
                    }
                    for(Item item : inventory.getItems()) {
                        if(item.getMonth().equals(commands[2])) {
                            sum += item.getPrice();
                        }
                    }
                    System.out.println("Expenses this month: " + sum);
                }
                if(Objects.equals(commands[1], "year")) {
                    for(Item item : inventory.getItems()) {
                        if(item.getYear().equals(commands[2])) {
                            sum += item.getPrice();
                        }
                    }
                    System.out.println("Expenses this year: " + sum);
                }
            }
        }
    }
}
