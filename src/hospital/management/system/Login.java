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
        // Main Screen setup - Ultra Premium Dark Cyber UI
        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(0, 0, 750, 500);
        mainPanel.setBackground(new Color(13, 17, 23)); // Pure Matte Carbon Base
        mainPanel.setLayout(null);
        add(mainPanel);

        // Sidebar Panel Layout Structure
        JPanel brandPanel = new JPanel();
        brandPanel.setBounds(0, 0, 320, 500);
        brandPanel.setBackground(new Color(22, 27, 34)); // Premium slate depth contrast
        brandPanel.setLayout(null);
        mainPanel.add(brandPanel);

        // Custom Neon Accent Vertical Bar Line Graphic Indicator
        JPanel neonBar = new JPanel();
        neonBar.setBounds(0, 0, 4, 500);
        neonBar.setBackground(new Color(156, 39, 176)); // Vibrant purple indicator edge
        brandPanel.add(neonBar);

        // Sidebar Rebranded Text Components
        JLabel brandTitle = new JLabel("HOSPITAL LOGIN");
        brandTitle.setBounds(35, 205, 260, 40);
        brandTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
        brandTitle.setForeground(new Color(240, 246, 252));
        brandPanel.add(brandTitle);

        // Sidebar Subtitle
        JLabel brandSub = new JLabel("Developed by: Yash Khatri");
        brandSub.setBounds(35, 245, 240, 20);
        brandSub.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        brandSub.setForeground(new Color(139, 148, 158));
        brandPanel.add(brandSub);

        // Right Section: Form Header Content Configuration
        JLabel loginHeader = new JLabel("Account Gateway");
        loginHeader.setBounds(375, 55, 300, 35);
        loginHeader.setFont(new Font("Segoe UI", Font.BOLD, 24));
        loginHeader.setForeground(Color.WHITE);
        mainPanel.add(loginHeader);

        JLabel loginSub = new JLabel("Provide operator clearance keys.");
        loginSub.setBounds(375, 92, 300, 20);
        loginSub.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        loginSub.setForeground(new Color(139, 148, 158));
        mainPanel.add(loginSub);

        // Username Label Configuration
        JLabel userLabel = new JLabel("SYSTEM OPERATOR IDENTITY");
        userLabel.setBounds(375, 145, 300, 20);
        userLabel.setFont(new Font("Segoe UI", Font.BOLD, 10));
        userLabel.setForeground(new Color(156, 39, 176)); 
        mainPanel.add(userLabel);

        // Field for Username
        textField = new JTextField();
        textField.setBounds(375, 170, 320, 42);
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textField.setBackground(new Color(22, 27, 34));
        textField.setForeground(Color.WHITE);
        textField.setCaretColor(Color.WHITE);
        textField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(48, 54, 61), 1),
                BorderFactory.createEmptyBorder(0, 15, 0, 15)
        ));
        mainPanel.add(textField);

        // Password Label Configuration
        JLabel passLabel = new JLabel("SECURITY ACCESS CREDENTIAL");
        passLabel.setBounds(375, 235, 300, 20);
        passLabel.setFont(new Font("Segoe UI", Font.BOLD, 10));
        passLabel.setForeground(new Color(156, 39, 176));
        mainPanel.add(passLabel);

        // Field for Password
        passwordField = new JPasswordField();
        passwordField.setBounds(375, 260, 320, 42);
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        passwordField.setBackground(new Color(22, 27, 34));
        passwordField.setForeground(Color.WHITE);
        passwordField.setCaretColor(Color.WHITE);
        passwordField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(48, 54, 61), 1),
                BorderFactory.createEmptyBorder(0, 15, 0, 15)
        ));
        mainPanel.add(passwordField);

        // Authenticate Button
        b1 = new JButton("Authenticate Session");
        b1.setBounds(375, 340, 320, 45);
        b1.setFont(new Font("Segoe UI", Font.BOLD, 13));
        b1.setBackground(new Color(156, 39, 176));
        b1.setForeground(Color.WHITE);
        b1.setFocusPainted(false);
        b1.setBorderPainted(false);
        b1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        mainPanel.add(b1);

        // Exit Button
        b2 = new JButton("Exit Terminal");
        b2.setBounds(375, 400, 320, 42);
        b2.setFont(new Font("Segoe UI", Font.BOLD, 12));
        b2.setBackground(new Color(13, 17, 23));
        b2.setForeground(new Color(248, 81, 73)); 
        b2.setFocusPainted(false);
        b2.setBorder(new LineBorder(new Color(248, 81, 73, 120), 1));
        b2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        mainPanel.add(b2);

        // Authenticate Listener Block
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                conn c = null; // Variable try block se pehle declare kiya taaki finally ko mil sake
                try {
                    c = new conn();
                    
                    String user = textField.getText();
                    String pass = new String(passwordField.getPassword());
                    
                    String q = "select * from login where ID = '" + user + "' and PW = '" + pass + "'";
                    ResultSet resultSet = c.statement.executeQuery(q);
                    
                    if (resultSet.next()) {
                        new Reception(); 
                        setVisible(false); 
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid Security Credentials!");
                    }
                    
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Exit Listener Block
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Frame View Geometry Parameters
        setUndecorated(true);
        setSize(750, 500);
        setLayout(null);
        setLocation(400, 200);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Login();
    }
}