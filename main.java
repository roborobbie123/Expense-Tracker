import java.io.*;
import java.util.*;

public class main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Inventory inventory = new Inventory();

        try(BufferedReader reader = new BufferedReader(new FileReader("expenses.csv"))){
            String line;
            List<String> data = new ArrayList<>();
            while((line = reader.readLine()) != null){
                data.add(line);
            }
            String dataString = data.toString().trim().replace("[", "").replace("]", "").replace(" ", "");
            String[] dataArray = dataString.split(",");

            if(dataArray[0].equals("id") && dataArray[1].equals("date") && dataArray[2].equals("description") && dataArray[3].equals("amount")){
                for(int i = 4; i < dataArray.length; i = i + 4){
                    Item item = new Item();
                    item.setId(Integer.parseInt(dataArray[i]));
                    item.setDate(dataArray[i+1]);
                    item.setName(dataArray[i+2]);
                    item.setPrice(Double.parseDouble(dataArray[i+3]));
                    inventory.addItem(item);
                }
            }
        }
        catch(IOException e){
            System.out.println("Add your first expense");
        }

        List<Item> items = inventory.getItems();
        int count;

        if(items.size() > 0){
            count = items.getLast().getId();
        } else {
            count = 0;
        }

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

            // add command
            if(commands[0].equals("add") && commands.length == 3) {
                String name = commands[1];
                double price = Double.parseDouble(commands[2]);
                if(price <= 0) {
                    System.out.println("Expense cannot be negative. Please try again.");
                    continue;
                }
                int id = count + 1;
                count++;
                inventory.addItem(new Item(name, price, id));
                System.out.println("Expense added successfully " + "(ID: " + id + ")");
            }

            // delete command
            if(commands[0].equals("delete") && commands.length == 2) {
                int id = Integer.parseInt(commands[1]);
                int counter = 0;
                for(int i = items.size() - 1; i >= 0 ; i--) {
                   Item item = items.get(i);
                   if(item.getId() == id) {
                       items.remove(i);
                       System.out.println("Expense deleted successfully " + "(ID: " + id + ")");
                       counter++;
                       if(items.size() == 0) {
                           System.out.println("Nothing left to delete");
                       }
                   }
                }
                if(counter == 0) {
                    System.out.println("Item not found. Please try again.");
                }
            }

            // overall summary command
            if(commands[0].equals("summary") && commands.length == 1) {
                double sum = 0;
                for(Item item : inventory.getItems()) {
                    sum += item.getPrice();
                }
                System.out.println("Total expenses: $" + sum);
            }
            // date specific summary command
            if(commands[0].equals("summary") && commands.length == 3) {
                double sum = 0;
                if(Objects.equals(commands[1], "month")) {
                    if(commands[2].length() == 1) {
                        commands[2] = '0' + commands[2];
                    }
                    for(Item item : inventory.getItems()) {
                        if(item.getMonth().equals(commands[2])) {
                            sum += item.getPrice();
                        }
                    }
                    System.out.println("Expenses this month: $" + sum);
                }
                if(Objects.equals(commands[1], "year")) {
                    for(Item item : inventory.getItems()) {
                        if(item.getYear().equals(commands[2])) {
                            sum += item.getPrice();
                        }
                    }
                    System.out.println("Expenses this year: $" + sum);
                }
            }
            //update command based on the 3 input being a number or word
            if(commands[0].equals("update") && commands.length == 3) {
                int id = Integer.parseInt(commands[1]);
                String update = commands[2];
                try {
                    int updatePrice = Integer.parseInt(update);
                    for(Item item : inventory.getItems()) {
                        if(item.getId() == id) {
                            item.setPrice(updatePrice);
                            System.out.println("Item " + item.getId() + " updated to $" + updatePrice);
                            break;
                        }
                    }
                }
                catch(NumberFormatException e) {
                    for(Item item : inventory.getItems()) {
                        if(item.getId() == id) {
                            item.setName(update);
                            System.out.println("Item " + item.getId() + " updated to " + update);
                            break;
                        }
                    }
                }
            }
            // writing expenses list to csv file
            try(FileWriter writer = new FileWriter("expenses.csv")) {
                writer.write("id,date,description,amount\n");
                for(Item item : inventory.getItems()) {
                    writer.write(item.getId() + "," + item.getDate() + "," + item.getName() + "," + item.getPrice() + "\n");
                }
            }
            catch(IOException e) {
                System.out.println("Error writing expenses.csv");
            }
        }
    }
}
