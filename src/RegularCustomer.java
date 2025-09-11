public class RegularCustomer extends Customer {
    public RegularCustomer(String name, String address, String phone, String type) {
        super(name, address, phone, type);
    }

    @Override
    public String payCoffee() {
        String str = "Regular Customers can pay with cash or card.\n";
        return str;
    }
}
