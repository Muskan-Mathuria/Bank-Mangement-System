package JavaApplication1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.util.Date;
import java.text.SimpleDateFormat;

public class FastCash extends JFrame implements ActionListener {

    JButton b1,b2,b3,b4,b5,b6,b7;
    String pin;

    FastCash(String pin){
        this.pin = pin;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("javaappphoto/atm2.png"));
        Image i2 = i1.getImage().getScaledInstance(1550,830,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(0,0,1550,830);
        add(l3);

        JLabel label = new JLabel("SELECT WITHDRAWL AMOUNT");
        label.setBounds(445,180,700,35);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("System",Font.BOLD,23));
        l3.add(label);

        b1 = new JButton("Rs. 100");
        b2 = new JButton("Rs. 500");
        b3 = new JButton("Rs. 1000");
        b4 = new JButton("Rs. 2000");
        b5 = new JButton("Rs. 5000");
        b6 = new JButton("Rs. 10000");
        b7 = new JButton("BACK");

        JButton[] btns = {b1,b2,b3,b4,b5,b6,b7};

        int x1 = 410, x2 = 700, y = 274;

        for (int i = 0; i < btns.length; i++) {
            JButton btn = btns[i];
            btn.setForeground(Color.WHITE);
            btn.setBackground(new Color(65,125,128));
            btn.setBounds((i%2==0?x1:x2), y,150,35);
            btn.addActionListener(this);
            l3.add(btn);

            if(i%2!=0) y += 44;
        }

        setLayout(null);
        setSize(1550,1080);
        setLocation(0,0);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == b7) {
            setVisible(false);
            new main_Class(pin);
            return;
        }

        try {
            // ✅ amount extract
            String amount = ((JButton)e.getSource()).getText().substring(4);

            Conn c = new Conn();
            ResultSet resultSet = c.statement.executeQuery(
                    "SELECT * FROM bank WHERE pin = '" + pin + "'"
            );

            // ✅ double use (IMPORTANT)
            double balance = 0;

            while (resultSet.next()) {
                double amt = Double.parseDouble(resultSet.getString("amount"));

                if (resultSet.getString("type").equals("Deposit")) {
                    balance += amt;
                } else {
                    balance -= amt;
                }
            }

            // ✅ balance check
            if (balance < Double.parseDouble(amount)) {
                JOptionPane.showMessageDialog(null, "Insufficient Balance");
                return;
            }

            // ✅ date format fix
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedDate = sdf.format(new Date());

            // ✅ insert correct
            c.statement.executeUpdate(
                    "INSERT INTO bank(pin, date, type, amount) VALUES('"
                            + pin + "', '" + formattedDate + "', 'Withdrawl', '" + amount + "')"
            );

            JOptionPane.showMessageDialog(null, "Rs. " + amount + " Debited Successfully");

            setVisible(false);
            new main_Class(pin);

        } catch (Exception E) {
            E.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new FastCash("");
    }
}
