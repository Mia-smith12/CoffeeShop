import java.util.List;

public class Coffee {
    private String name;
    private double price;
    private int calories;
    private String status;
    public Coffee(String name, double price, int calories){
        this.name = name;
        this.price = price;
        this.calories = calories;
        this.status = "Order: Active\n";
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    @Override
    public String toString() {
        String str = "Drink: " + getName() + " Price: $"+ getPrice() +" Calories: "+getCalories();
        return str;
    }

    public String prepare() {
        return null;
    }
    public void markAsPass(){
        this.status = "Order: Passive \n";
    }




}
