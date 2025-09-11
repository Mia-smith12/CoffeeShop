import java.util.ArrayList;


public abstract class Customer {
    private String name;
    private String address;
    private String phone;
    private String type;
    private static ArrayList<Coffee> coffeeList;


    public Customer(String name,String address,String phone, String type){
        this.name=name;
        this.address=address;
        this.phone=phone;
        this.type = type;
        this.coffeeList = new ArrayList<>();
    }
    public static void addCoffee(Coffee coffee) {
        coffeeList.add(coffee);
    }

    public ArrayList<Coffee> getOrders() {
        return coffeeList;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
       String str = "Name: "+getName()+" Address: "+getAddress()+" Phone Number: "+getPhone();
        return str;

    }
    public abstract String payCoffee();


}
