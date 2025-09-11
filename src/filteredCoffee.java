public class filteredCoffee extends Coffee{

    private String Roast;
    private String status;

    public filteredCoffee(String name, double price, int calories, String Roast) {
        super(name, price, calories);
        this.Roast = Roast;

    }

    public String getRoast() {
        return Roast;
    }

    public void setDarkRoast(String Roast) {
        this.Roast = Roast;
    }


    public String toString() {
        return super.toString() + " Roast: "+getRoast();

    }

    @Override
    public String prepare() {
        String prep ="Your drink is being prepared using "+getRoast()+" roast!";
        return prep;
    }
}
