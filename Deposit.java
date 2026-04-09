package JavaApplication1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.PreparedStatement;

public class Deposit extends JFrame implements ActionListener {

    String pin;
    TextField textField;
    JButton b1, b2;

    Deposit(String pin){
        this.pin = pin;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("javaappphoto/atm2.png"));
        Image i2 = i1.getImage().getScaledInstance(1550,830,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(0,0,1550,830);
        add(l3);

        JLabel label1 = new JLabel("ENTER AMOUNT YOU WANT TO DEPOSIT");
        label1.setForeground(Color.WHITE);
        label1.setFont(new Font("System", Font.BOLD, 16));
        label1.setBounds(460,180,400,35);
        l3.add(label1);

        textField = new TextField();
        textField.setBackground(new Color(65,125,128));
        textField.setForeground(Color.WHITE);
        textField.setBounds(460,230,320,25);
        textField.setFont(new Font("Raleway", Font.BOLD,22));
        l3.add(textField);

        b1 = new JButton("DEPOSIT");
        b1.setBounds(700,362,150,35);
        b1.setBackground(new Color(65,125,128));
        b1.setForeground(Color.WHITE);
        b1.addActionListener(this);
        l3.add(b1);

        b2 = new JButton("BACK");
        b2.setBounds(700,406,150,35);
        b2.setBackground(new Color(65,125,128));
        b2.setForeground(Color.WHITE);
        b2.addActionListener(this);
        l3.add(b2);

        setLayout(null);
        setSize(1550,1080);
        setLocation(0,0);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            String amount = textField.getText();

            if (e.getSource() == b1) {
                if (amount.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter amount");
                } else {

                    // ✅ Date format fix
                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = sdf.format(date);

                    Conn c = new Conn();

                    // ✅ PreparedStatement (safe & best)
                    String query = "INSERT INTO bank(pin, date, type, amount) VALUES (?, ?, ?, ?)";
                    PreparedStatement ps = c.connection.prepareStatement(query);

                    ps.setString(1, pin);
                    ps.setString(2, formattedDate);
                    ps.setString(3, "Deposit");
                    ps.setDouble(4, Double.parseDouble(amount));

                    ps.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Rs. " + amount + " Deposited Successfully");

                    setVisible(false);
                    new main_Class(pin);
                }

            } else if (e.getSource() == b2) {
                setVisible(false);
                new main_Class(pin);
            }

        } catch (Exception E) {
            E.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Deposit("");
    }
}
