import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Locale;

public class GUI extends JFrame {


    public static ArrayList<Customer> CustomerArrayList = new ArrayList<>();
    static Color color = new Color(200,180,225);

    public static void start(){
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    JFrame frame = new JFrame();
                    frame.setSize(550, 600);
                    frame.setTitle("Coffee Shop");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setLocationRelativeTo(null);
                    try {
                        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    frame.setLayout(null);
                    createMainPanel(frame);
// uncomment if you want to pack all components// closely together
 //frame.pack();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void createMainPanel(JFrame frame) {
        JPanel menu = new Image("coffee.jpeg");
        // to hold all menu options: Order coffee, prepare, etc
         menu.setLayout(null); // null allows to set the panels components manually; aka setbounds

        JButton orderCoffee = new JButton("Order Coffee");
       orderCoffee.setBounds(50,100,100,40);
        menu.add(orderCoffee);

       JButton viewOrder = new JButton ("View Order");
        viewOrder.setBounds( 165,100, 100, 40);
        menu.add(viewOrder);

        JButton prepare = new JButton("Prepare");
        prepare.setBounds( 275,100, 100, 40);
        menu.add(prepare);

        JButton exit = new JButton("Exit");
        exit.setBounds( 390,100, 100, 40);
        menu.add(exit);

        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                File.writeToFile(CustomerArrayList);
                System.exit(0);
            }
        });

        prepare.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame prepareFrame = new JFrame("Preparing");
                prepareFrame.setSize(500, 200);
                prepareFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                prepareFrame.setLocationRelativeTo(frame);

                JTextArea preppy = new JTextArea();
                preppy.setEditable(false);
                preppy.setBounds(10, 10, 500, 200 );

                for (Customer customer : CustomerArrayList) {
                    for (Coffee coffee : customer.getOrders()) {
                        preppy.append(coffee.prepare()+ "\n");
                        coffee.markAsPass();
                        preppy.append(coffee.getStatus());
                    }
                }
                JPanel preparePanel = new JPanel(new BorderLayout());
                Color lightBlue = new Color(173, 216, 230);
                preppy.setBackground(lightBlue);
                preparePanel.add(preppy);
                prepareFrame.add(preparePanel);
                prepareFrame.setVisible(true);

                frame.revalidate();
                frame.repaint();
            }
        });

//adding every customer and coffee type within arraylist tor print
        orderCoffee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame customerInfo = new JFrame("Customer Information");
                customerInfo.setSize(550, 200);
                customerInfo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                customerInfo.setLocationRelativeTo(frame);

                JLabel nameLabel = new JLabel("Customer Name: ");
                JTextField nameField = new JTextField();
                nameField.setPreferredSize(new Dimension(150, 30));
                JLabel numberLabel = new JLabel("Customer Number: ");
                JTextField numberField = new JTextField();
                numberField.setPreferredSize(new Dimension(150, 30));
                JLabel addyLabel = new JLabel("Customer Address:");
                JTextField addyField = new JTextField();
                addyLabel.setPreferredSize(new Dimension(150, 30));
                JLabel typeLabel = new JLabel("Regular or Premium Customer:(yes/no):");
                JTextField typeField = new JTextField();
                typeLabel.setPreferredSize(new Dimension(150, 30));
                JButton confirm = new JButton("Confirm");
               JPanel Info = new JPanel(new GridLayout(5,2,5, 5)); // 5 * 5 horizontal and vertical gap between rows and coll.
                Info.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                Info.setBackground(color);

                Info.add(nameLabel);
                Info.add(nameField);
                Info.add(numberLabel);
                Info.add(numberField);
                Info.add(addyLabel);
                Info.add(addyField);
                Info.add(typeLabel);
                Info.add(typeField);
                Info.add(confirm);

                customerInfo.add(Info);
                customerInfo.setVisible(true);

                confirm.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // user's input for shots and milk type
                        String name = nameField.getText();
                        String number = numberField.getText();
                        String addy = addyField.getText();
                        String type = typeField.getText().toLowerCase(Locale.ROOT);

                           if(type.equals("yes")) {
                               premiumCustomer customer = new premiumCustomer(name, addy, number, type);
                               CustomerArrayList.add(customer);

                           }
                        else{
                            RegularCustomer regularCustomer = new RegularCustomer(name,addy,number,type);
                            CustomerArrayList.add(regularCustomer);
                        }

                        customerInfo.dispose();
                    }
                });

//done with customer info// adding menu buttons + action listeners
                JButton latte = new JButton("Latte");
                JButton cap = new JButton("Cappuccino");
                JButton frappe = new JButton("Frappe");
                JButton coldBrew = new JButton("Cold Brew");
                JButton dripCoffee = new JButton("Drip Coffee");
                latte.setBounds(50, 150, 100, 30);
                cap.setBounds(50, 200, 100, 30);
                frappe.setBounds(50, 250, 100, 30);
                coldBrew.setBounds(50, 300, 100, 30);
                dripCoffee.setBounds(50, 350, 100, 30);
                menu.add(latte);
                menu.add(cap);
                menu.add(frappe);
                menu.add(coldBrew);
                menu.add(dripCoffee);

                // ActionListeners for coffee buttons
                cap.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JFrame customizeDrink = new JFrame("Customize Cappuccino");
                        customizeDrink.setSize(300, 200);
                        customizeDrink.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        customizeDrink.setLocationRelativeTo(frame);

                        //  components for customizing the latte
                        JLabel shotsLabel = new JLabel("Number of shots:");
                        JTextField shotsField = new JTextField(10);
                        JLabel milkLabel = new JLabel("Type of milk:");
                        JTextField milkField = new JTextField(10);

                        //  button to confirm customization
                        JButton confirmButton = new JButton("Confirm");

                        JPanel panel = new JPanel(new GridLayout(3, 2));
                        panel.setBackground(color);
                        panel.add(shotsLabel);
                        panel.add(shotsField);
                        panel.add(milkLabel);
                        panel.add(milkField);
                        panel.add(confirmButton);

                        customizeDrink.add(panel);
                        customizeDrink.setVisible(true);

                        String name2 = nameField.getText();
                        confirmButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                // user's input for shots and milk type
                                String shots = shotsField.getText();
                                String milk = milkField.getText();

                                int shotsInt = Integer.parseInt(shots);
                                int cals= 100;
                                cals*= shotsInt;
                                double price = 1.50;
                                price*=shotsInt;

                                espresso latte = new espresso("Cappuccino", price, cals, shots, milk);
                                for (Customer customer : CustomerArrayList) {
                                    if (customer.getName().equals(name2)) {
                                       customer.addCoffee(latte);
                                    }
                                }
                                customizeDrink.dispose();
                            }
                        });
                    }
                });

                        latte.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JFrame customizeDrink = new JFrame("Customize Latte");
                        customizeDrink.setSize(300, 200);
                        customizeDrink.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        customizeDrink.setLocationRelativeTo(frame);

                        //  components for customizing the latte
                        JLabel shotsLabel = new JLabel("Number of shots:");
                        JTextField shotsField = new JTextField(10);
                        JLabel milkLabel = new JLabel("Type of milk:");
                        JTextField milkField = new JTextField(10);

                        //  button to confirm customization
                        JButton confirmButton = new JButton("Confirm");

                        JPanel panel = new JPanel(new GridLayout(3, 2));
                        panel.setBackground(color);
                        panel.add(shotsLabel);
                        panel.add(shotsField);
                        panel.add(milkLabel);
                        panel.add(milkField);
                        panel.add(confirmButton);

                        customizeDrink.add(panel);
                        customizeDrink.setVisible(true);
                        String name2 = nameField.getText();
                        confirmButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                // user's input for shots and milk type
                                String shots = shotsField.getText();
                                String milk = milkField.getText();

                                int shotsInt = Integer.parseInt(shots);
                                int cals= 100;
                                cals*= shotsInt;
                                double price = 1.50;
                                price*=shotsInt;

                                espresso latte = new espresso("Latte", price, cals, shots, milk);
                                for (Customer customer : CustomerArrayList) {
                                    if (customer.getName().equals(name2)) {
                                        customer.addCoffee(latte);
                                    }
                                }

                                customizeDrink.dispose();
                            }
                        });
                 } });

                frappe.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JFrame customizeDrink = new JFrame("Customize Frappuccino");
                        customizeDrink.setSize(300, 200);
                        customizeDrink.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        customizeDrink.setLocationRelativeTo(frame);
                        //  components for customizing the latte
                        JLabel shotsLabel = new JLabel("Number of shots:");
                        JTextField shotsField = new JTextField(10);
                        JLabel milkLabel = new JLabel("Type of milk:");
                        JTextField milkField = new JTextField(10);
                        //  button to confirm customization
                        JButton confirmButton = new JButton("Confirm");

                        JPanel panel = new JPanel(new GridLayout(3, 2));
                        panel.setBackground(color);
                        panel.add(shotsLabel);
                        panel.add(shotsField);
                        panel.add(milkLabel);
                        panel.add(milkField);
                        panel.add(confirmButton);

                        customizeDrink.add(panel);
                        customizeDrink.setVisible(true);

                        String name2 = nameField.getText();
                        confirmButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                // user's input for shots and milk type
                                String shots = shotsField.getText();
                                String milk = milkField.getText();

                                int shotsInt = Integer.parseInt(shots);
                                int cals= 100;
                                cals*= shotsInt;

                                double price = 1.50;
                                price*=shotsInt;

                                espresso latte = new espresso("Frappe", price, cals, shots, milk);
                                for (Customer customer : CustomerArrayList) {
                                    if (customer.getName().equals(name2)) {
                                        customer.addCoffee(latte);
                                    }
                                }
                                customizeDrink.dispose();
                            }
                        });
                    }});
                coldBrew.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JFrame customizeDrink = new JFrame("Customize ColdBrew");
                        customizeDrink.setSize(300, 200);
                        customizeDrink.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        customizeDrink.setLocationRelativeTo(frame);
                        JLabel roastLabel = new JLabel("Dark or Light Roast:");
                        JTextField roastField = new JTextField(10);
                        JButton confirmButton = new JButton("Confirm");
                        JPanel panel = new JPanel(new GridLayout(3, 2));
                        panel.setBackground(color);
                        panel.add(roastLabel);
                        panel.add(roastField);
                        panel.add(confirmButton);

                        customizeDrink.add(panel);
                        customizeDrink.setVisible(true);

                        String name2 = nameField.getText();
                        confirmButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                // user's input for shots and milk type
                                String roast = roastField.getText();

                               filteredCoffee coffe = new filteredCoffee("Cold Brew", 2.50, 85, roast);
                                for (Customer customer : CustomerArrayList) {
                                    if (customer.getName().equals(name2)) {
                                        customer.addCoffee(coffe);
                                    }
                                }
                                customizeDrink.dispose();
                            }
                        });
                    }
                });
                dripCoffee.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JFrame customizeDrink = new JFrame("Customize Drip Coffee");
                        customizeDrink.setSize(300, 200);
                        customizeDrink.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        customizeDrink.setLocationRelativeTo(frame);

                        JLabel roastLabel = new JLabel("Dark or Light Roast:");
                        JTextField roastField = new JTextField(10);

                        JButton confirmButton = new JButton("Confirm");
                        JPanel panel = new JPanel(new GridLayout(3, 2));
                        panel.setBackground(color);
                        panel.add(roastLabel);
                        panel.add(roastField);
                        panel.add(confirmButton);

                        customizeDrink.add(panel);
                        customizeDrink.setVisible(true);

                        String name2 = nameField.getText();
                        confirmButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                // user's input for shots and milk type
                                String roast = roastField.getText();

                                filteredCoffee coffe = new filteredCoffee("Drip Coffee", 3.00, 85, roast);
                                for (Customer customer : CustomerArrayList) {
                                    if (customer.getName().equals(name2)) {
                                        customer.addCoffee(coffe);
                                    }
                                }
                                customizeDrink.dispose();
                            }
                        });
                    }
                });
//neccessary when adding new things to panels
                frame.revalidate();
                frame.repaint();

            }
        });
//view orders holds a panel within a new frame to display the orders list using a for loop and arraylists
        viewOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame ordersFrame = new JFrame("View Orders");
                ordersFrame.setSize(500, 250);
                ordersFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                ordersFrame.setLocationRelativeTo(frame);

                JTextArea orders = new JTextArea();
                orders.setEditable(false);
                orders.setBounds(10, 10, 500, 250 );
               //setting customers in arraylists trying to print type of customers within view orders. Need new arraylist for customer?
                for (Customer customer : CustomerArrayList) {
                    orders.append( customer.toString() + "\n");
                    for (Coffee coffee : customer.getOrders()) {
                        orders.append(coffee.toString() + "\n");
                        orders.append(coffee.getStatus());
                    }
                    orders.append("Payment: "+ customer.payCoffee()+"\n");
                }
                Color lightYellow = new Color(255, 255, 204);
                JPanel viewOrdersP = new JPanel(new BorderLayout());
                orders.setBackground(lightYellow);
                viewOrdersP.add(orders, BorderLayout.CENTER);
                ordersFrame.getContentPane().add(viewOrdersP);
                ordersFrame.setVisible(true);

                frame.revalidate();
                frame.repaint();

            }
        });
        menu.add(orderCoffee);
        menu.setBounds(0, 0, 600, 600);
        frame.getContentPane().add(menu);
    }
}

