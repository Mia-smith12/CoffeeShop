public class espresso extends Coffee{
    public String shots;
    public String milk;
    public String hotOrIced;


    public espresso(String name, double price, int calories, String shots, String milk) {
        super(name, price, calories);
        this.shots = shots;
        this.milk = milk;

    }

    public String getShots() {
        return shots;
    }

    public void setShots(String shots) {
        this.shots = shots;
    }

    public String getMilk() {
        return milk;
    }

    public void setMilk(String milk) {
        this.milk = milk;
    }

    @Override
    public String toString() {
        return super.toString() + " Shots: "+getShots() +" Milk: " +getMilk();

    }
    public String prepare() {
        String prep;
        if(getShots().equals( "one") || getShots().equals("1")){
         prep ="Your drink is being prepared with "+getShots()+" espresso shot and with " + getMilk() +" milk!";
        }
        else {
             prep ="Your drink is being prepared with "+getShots()+" espresso shots and with " + getMilk() +" milk!";
        }
        return prep;
    }
}
