package JavaApplication1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Signup2 extends JFrame implements ActionListener {

    JComboBox<String> comboBox, comboBox2, comboBox3, comboBox4, comboBox5;
    JTextField textPan, textAadhar;
    JRadioButton r1, r2, e1, e2;
    JButton next;
    String formno;

    Signup2(String formno) {
        super("APPLICATION FORM");

        this.formno = formno;

        JLabel l1 = new JLabel("Page 2 : Additional Details");
        l1.setFont(new Font("Raleway", Font.BOLD, 22));
        l1.setBounds(250, 30, 400, 40);
        add(l1);

        // Religion
        JLabel l3 = new JLabel("Religion:");
        l3.setBounds(100, 100, 150, 30);
        add(l3);

        String religion[] = {"Hindu", "Muslim", "Sikh", "Christian", "Other"};
        comboBox = new JComboBox<>(religion);
        comboBox.setBounds(300, 100, 250, 30);
        add(comboBox);

        // Category
        JLabel l4 = new JLabel("Category:");
        l4.setBounds(100, 150, 150, 30);
        add(l4);

        String category[] = {"General", "OBC", "SC", "ST", "Other"};
        comboBox2 = new JComboBox<>(category);
        comboBox2.setBounds(300, 150, 250, 30);
        add(comboBox2);

        // Income
        JLabel l5 = new JLabel("Income:");
        l5.setBounds(100, 200, 150, 30);
        add(l5);

        String income[] = {"Select", "<1,50,000", "<2,50,000", "5,00,000", "Up to 10,00,000", "Above 10,00,000"};
        comboBox3 = new JComboBox<>(income);
        comboBox3.setBounds(300, 200, 250, 30);
        add(comboBox3);

        // Education
        JLabel l6 = new JLabel("Education:");
        l6.setBounds(100, 250, 150, 30);
        add(l6);

        String education[] = {"Non-Graduate", "Graduate", "Post-Graduate", "Doctorate", "Others"};
        comboBox4 = new JComboBox<>(education);
        comboBox4.setBounds(300, 250, 250, 30);
        add(comboBox4);

        // Occupation
        JLabel l7 = new JLabel("Occupation:");
        l7.setBounds(100, 300, 150, 30);
        add(l7);

        String occupation[] = {"Salaried", "Self-Employed", "Business", "Student", "Retired", "Other"};
        comboBox5 = new JComboBox<>(occupation);
        comboBox5.setBounds(300, 300, 250, 30);
        add(comboBox5);

        // PAN
        JLabel l8 = new JLabel("PAN Number:");
        l8.setBounds(100, 350, 150, 30);
        add(l8);

        textPan = new JTextField();
        textPan.setBounds(300, 350, 250, 30);
        add(textPan);

        // Aadhar
        JLabel l9 = new JLabel("Aadhar Number:");
        l9.setBounds(100, 400, 150, 30);
        add(l9);

        textAadhar = new JTextField();
        textAadhar.setBounds(300, 400, 250, 30);
        add(textAadhar);

        // Senior Citizen
        JLabel l10 = new JLabel("Senior Citizen:");
        l10.setBounds(100, 450, 150, 30);
        add(l10);

        r1 = new JRadioButton("Yes");
        r2 = new JRadioButton("No");

        ButtonGroup bg1 = new ButtonGroup();
        bg1.add(r1);
        bg1.add(r2);

        r1.setBounds(300, 450, 70, 30);
        r2.setBounds(380, 450, 70, 30);

        add(r1);
        add(r2);

        // Existing Account
        JLabel l11 = new JLabel("Existing Account:");
        l11.setBounds(100, 500, 150, 30);
        add(l11);

        e1 = new JRadioButton("Yes");
        e2 = new JRadioButton("No");

        ButtonGroup bg2 = new ButtonGroup();
        bg2.add(e1);
        bg2.add(e2);

        e1.setBounds(300, 500, 70, 30);
        e2.setBounds(380, 500, 70, 30);

        add(e1);
        add(e2);

        // Button
        next = new JButton("Next");
        next.setBounds(300, 570, 100, 30);
        next.addActionListener(this);
        add(next);

        setLayout(null);
        setSize(700, 650);
        setLocation(400, 100);
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String rel = (String) comboBox.getSelectedItem();
        String cate = (String) comboBox2.getSelectedItem();
        String inc = (String) comboBox3.getSelectedItem();
        String edu = (String) comboBox4.getSelectedItem();
        String occ = (String) comboBox5.getSelectedItem();

        String pan = textPan.getText();
        String aadhar = textAadhar.getText();

        String scitizen = r1.isSelected() ? "Yes" : "No";
        String eAccount = e1.isSelected() ? "Yes" : "No";

        try {
            if (pan.equals("") || aadhar.equals("") || inc.equals("Select")) {
                JOptionPane.showMessageDialog(null, "Please fill all fields");
                return;
            }

            Conn c = new Conn();

            String q = "INSERT INTO signuptwo VALUES ('" + formno + "','" + rel + "','" + cate + "','" + inc + "','" + edu + "','" + occ + "','" + pan + "','" + aadhar + "','" + scitizen + "','" + eAccount + "')";

            c.statement.executeUpdate(q);

            new Signup3(formno);
            setVisible(false);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Signup2("1234");
    }
}
