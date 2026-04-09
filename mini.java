package JavaApplication1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class mini extends JFrame implements ActionListener {

    String pin;
    JTextArea area;
    JButton b1;

    mini(String pin){
        this.pin = pin;

        setTitle("Mini Statement");

        JLabel label1 = new JLabel("INDIAN BANK");
        label1.setBounds(150,20,300,40);
        label1.setFont(new Font("System", Font.BOLD, 20));
        add(label1);

        JLabel label2 = new JLabel("Card Number: XXXX-XXXX-XXXX");
        label2.setBounds(100,60,400,30);
        add(label2);

        area = new JTextArea();
        area.setBounds(20,120,450,300);
        area.setFont(new Font("Monospaced", Font.PLAIN, 14));
        add(area);

        b1 = new JButton("BACK");
        b1.setBounds(180,450,120,40);
        b1.addActionListener(this);
        add(b1);

        // ✅ Fetch data from DB
        try {
            Conn c = new Conn();
            ResultSet resultSet = c.statement.executeQuery(
                    "SELECT * FROM bank WHERE pin = '" + pin + "'"
            );

            double balance = 0;

            while (resultSet.next()) {

                String date = resultSet.getString("date");
                String type = resultSet.getString("type");
                String amount = resultSet.getString("amount");

                // ✅ show transaction
                area.append(date + "    " + type + "    Rs. " + amount + "\n");

                // ✅ calculate balance
                double amt = Double.parseDouble(amount);

                if (type.equals("Deposit")) {
                    balance += amt;
                } else {
                    balance -= amt;
                }
            }

            area.append("\n-----------------------------\n");
            area.append("Current Balance: Rs. " + balance);

        } catch (Exception e) {
            e.printStackTrace();
        }

        setLayout(null);
        setSize(500,550);
        setLocation(300,100);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        new main_Class(pin);
    }

    public static void main(String[] args) {
        new mini("");
    }
}
