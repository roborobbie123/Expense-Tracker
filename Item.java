import java.text.SimpleDateFormat;
import java.util.Date;

public class Item {
    private int price;
    private String name;
    private int id;
    private Date date;

    public Item() {}

    public Item(String name, int price, int id) {
        this.name = name;
        this.price = price;
        this.id = id;
        this.date = new Date();
    }

    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);
        return dateString;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public String getYear() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);
        String year = dateString.substring(0, 4);
        return year;
    }
    public String getMonth() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);
        String month = dateString.substring(5, 7);
        return month;
    }
    public String getDay() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);
        String day = dateString.substring(8);
        return day;
    }


}
