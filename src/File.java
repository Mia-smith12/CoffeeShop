
    import java.io.*;
import java.util.ArrayList;
    import java.util.Scanner;

    public class File {

        public static void writeToFile(ArrayList<Customer> customers) {
            try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("CoffeeOrders.txt")))) {
                for (Customer customer : customers) {
                    out.println(customer.toString());
                    for (Coffee coffee : customer.getOrders()) {
                        out.println(coffee.toString());
                    }
                    out.println("Payment: " + customer.payCoffee());
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }



