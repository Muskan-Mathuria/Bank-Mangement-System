package JavaApplication1;
// ye package name vahi dalega jo tumhare project ka name hoga 

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class BalanceEnquriy extends JFrame implements ActionListener {

    String pin;
    JLabel label2;
    JButton b1;

    BalanceEnquriy(String pin){
        this.pin = pin;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("javaappphoto/atm2.png"));
        Image i2 = i1.getImage().getScaledInstance(1550,830,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(0,0,1550,830);
        add(l3);

        JLabel label1 = new JLabel("Your Current Balance is:");
        label1.setForeground(Color.WHITE);
        label1.setFont(new Font("System", Font.BOLD, 18));
        label1.setBounds(430,180,400,35);
        l3.add(label1);

        label2 = new JLabel();
        label2.setForeground(Color.GREEN);
        label2.setFont(new Font("System", Font.BOLD, 22));
        label2.setBounds(430,220,400,35);
        l3.add(label2);

        b1 = new JButton("BACK");
        b1.setBounds(700,406,150,35);
        b1.setBackground(new Color(65,125,128));
        b1.setForeground(Color.WHITE);
        b1.addActionListener(this);
        l3.add(b1);

        // ✅ BALANCE CALCULATION (FIXED)
        double balance = 0;

        try {
            Conn c = new Conn();
            ResultSet resultSet = c.statement.executeQuery(
                    "SELECT * FROM bank WHERE pin = '" + pin + "'"
            );

            while (resultSet.next()) {

                double amount = Double.parseDouble(resultSet.getString("amount"));

                if (resultSet.getString("type").equals("Deposit")) {
                    balance += amount;
                } else {
                    balance -= amount;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // ✅ SHOW BALANCE
        label2.setText("Rs. " + balance);

        setLayout(null);
        setSize(1550,1080);
        setLocation(0,0);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        new main_Class(pin);
    }

    public static void main(String[] args) {
        new BalanceEnquriy("");
    }
}
