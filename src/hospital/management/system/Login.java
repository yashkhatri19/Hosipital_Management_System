package hospital.management.system;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login extends JFrame {

    JTextField textField;
    JPasswordField passwordField;
    JButton b1, b2;

    Login() {
        // Main Screen setup - Dark Theme Luxury
        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(0, 0, 750, 500);
        mainPanel.setBackground(new Color(18, 22, 26)); // Deep Cyber Dark Background
        mainPanel.setLayout(null);
        add(mainPanel);

        // Decorative Sidebar Graphic Area (Left Panel)
        JPanel brandPanel = new JPanel();
        brandPanel.setBounds(0, 0, 320, 500);
        brandPanel.setBackground(new Color(27, 32, 40)); // Slightly lighter matte finish
        brandPanel.setLayout(null);
        mainPanel.add(brandPanel);

        // Sidebar App Branding Title
        JLabel brandTitle = new JLabel("HOSPITAL OS");
        brandTitle.setBounds(40, 200, 260, 40);
        brandTitle.setFont(new Font("Segoe UI", Font.BOLD, 28));
        brandTitle.setForeground(new Color(230, 235, 245));
        brandPanel.add(brandTitle);

        JLabel brandSub = new JLabel("Management Dashboard v2.0");
        brandSub.setBounds(40, 240, 240, 20);
        brandSub.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        brandSub.setForeground(new Color(110, 125, 140));
        brandPanel.add(brandSub);

        // Right Section: Clean Interactive Login Form Container
        JLabel loginHeader = new JLabel("Account Login");
        loginHeader.setBounds(370, 50, 300, 35);
        loginHeader.setFont(new Font("Segoe UI", Font.BOLD, 26));
        loginHeader.setForeground(Color.WHITE);
        mainPanel.add(loginHeader);

        JLabel loginSub = new JLabel("Enter system operator credentials below.");
        loginSub.setBounds(370, 90, 300, 20);
        loginSub.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        loginSub.setForeground(new Color(140, 150, 165));
        mainPanel.add(loginSub);

        // Username Input field
        JLabel userLabel = new JLabel("USERNAME");
        userLabel.setBounds(370, 145, 200, 20);
        userLabel.setFont(new Font("Segoe UI", Font.BOLD, 11));
        userLabel.setForeground(new Color(156, 39, 176)); // Vibrant Purple Accent
        mainPanel.add(userLabel);

        textField = new JTextField();
        textField.setBounds(370, 170, 320, 42);
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textField.setBackground(new Color(27, 32, 40));
        textField.setForeground(Color.WHITE);
        textField.setCaretColor(Color.WHITE);
        textField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(50, 60, 75), 1),
                BorderFactory.createEmptyBorder(0, 12, 0, 12)
        ));
        mainPanel.add(textField);

        // Password Input field
        JLabel passLabel = new JLabel("PASSWORD");
        passLabel.setBounds(370, 235, 200, 20);
        passLabel.setFont(new Font("Segoe UI", Font.BOLD, 11));
        passLabel.setForeground(new Color(156, 39, 176));
        mainPanel.add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(370, 260, 320, 42);
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        passwordField.setBackground(new Color(27, 32, 40));
        passwordField.setForeground(Color.WHITE);
        passwordField.setCaretColor(Color.WHITE);
        passwordField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(50, 60, 75), 1),
                BorderFactory.createEmptyBorder(0, 12, 0, 12)
        ));
        mainPanel.add(passwordField);

        // Action Trigger Buttons (Glow Purple Submit)
        b1 = new JButton("Authenticate");
        b1.setBounds(370, 340, 320, 45);
        b1.setFont(new Font("Segoe UI", Font.BOLD, 14));
        b1.setBackground(new Color(156, 39, 176)); // Bright Cyber Purple Glow
        b1.setForeground(Color.WHITE);
        b1.setFocusPainted(false);
        b1.setBorderPainted(false);
        mainPanel.add(b1);

        // Secondary Cancel Button
        b2 = new JButton("Exit System");
        b2.setBounds(370, 400, 320, 40);
        b2.setFont(new Font("Segoe UI", Font.BOLD, 13));
        b2.setBackground(new Color(18, 22, 26));
        b2.setForeground(new Color(198, 40, 40)); // Warning Red
        b2.setFocusPainted(false);
        b2.setBorder(new LineBorder(new Color(198, 40, 40), 1));
        mainPanel.add(b2);

        // Button Action Listeners linking to Backend DB
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    conn c = new conn();
                    String user = textField.getText();
                    String pass = new String(passwordField.getPassword());

                    String q = "select * from login where ID = '"+user+"' and PW = '"+pass+"'";
                    ResultSet resultSet = c.statement.executeQuery(q);

                    if (resultSet.next()) {
                        new Reception();
                        setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid Security Token/Credentials");
                    }
                } catch (Exception E) {
                    E.printStackTrace();
                }
            }
        });

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Frameless Borderless Glass Design parameters
        setUndecorated(true);
        setSize(750, 500);
        setLayout(null);
        setLocation(400, 250);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Login();
    }
}