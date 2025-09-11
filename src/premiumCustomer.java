public class premiumCustomer extends Customer{
    public premiumCustomer(String name, String address, String phone, String type) {
        super(name, address, phone, type);
    }

    @Override
    public String payCoffee() {
        String premium = "Premium customers may pay with card, cash, ApplePay, or CashApp."+"\n"+"You receive 10% off your order.\n";
        return premium;
    }

}
