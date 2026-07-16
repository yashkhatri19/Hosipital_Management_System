package hospital.management.system;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.ResultSet;

public class update_patient_details extends JFrame {

    // Global variables declared to ensure data state updates correctly inside action blocks
    private JComboBox<String> patientChoice;
    private JTextField textFieldR;
    private JTextField textFieldINTIme;
    private JTextField textFieldAmount;
    private JTextField textFieldPending;

    public update_patient_details() {
        // Main structural base canvas setup
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 950, 500);
        panel.setBackground(new Color(242, 246, 250)); // Pure Hospital Dashboard Matte Light base
        panel.setBorder(new LineBorder(new Color(218, 226, 236), 1));
        panel.setLayout(null);
        add(panel);

        // Section Typography Branding Module
        JLabel formTitle = new JLabel("PATIENT DATABASE TERMINAL");
        formTitle.setBounds(45, 30, 400, 30);
        formTitle.setForeground(new Color(22, 42, 68)); // High contrast Slate tone
        formTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        panel.add(formTitle);

        JLabel formSub = new JLabel("Synchronize and check live active accounting status logs");
        formSub.setBounds(45, 58, 450, 20);
        formSub.setForeground(new Color(120, 135, 155));
        formSub.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        panel.add(formSub);

        // Left Component Box Layer: Modern Structured Workspace Card
        JPanel formContainer = new JPanel();
        formContainer.setBounds(45, 105, 490, 355);
        formContainer.setBackground(Color.WHITE);
        formContainer.setBorder(new LineBorder(new Color(228, 235, 242), 1));
        formContainer.setLayout(null);
        panel.add(formContainer);

        // Typography styling parameters
        Font inputHeaderFont = new Font("Segoe UI", Font.BOLD, 13);
        Color elementLabelColor = new Color(74, 88, 108);

        // 1. Selector Module
        JLabel lblName = new JLabel("Active Patient Identity");
        lblName.setBounds(25, 25, 160, 20);
        lblName.setFont(inputHeaderFont);
        lblName.setForeground(elementLabelColor);
        formContainer.add(lblName);

        patientChoice = new JComboBox<>();
        patientChoice.setBounds(225, 20, 240, 34);
        patientChoice.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        patientChoice.setBackground(Color.WHITE);
        patientChoice.setCursor(new Cursor(Cursor.HAND_CURSOR));
        formContainer.add(patientChoice);

        // Populating relational schema keys safely
        try {
            conn c = new conn();
            ResultSet resultSet = c.statement.executeQuery("select * from Patient_Info");
            while (resultSet.next()) {
                patientChoice.addItem(resultSet.getString("Name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 2. Room Data Entry Node
        JLabel lblRoom = new JLabel("Assigned Room Key");
        lblRoom.setBounds(25, 75, 160, 20);
        lblRoom.setFont(inputHeaderFont);
        lblRoom.setForeground(elementLabelColor);
        formContainer.add(lblRoom);

        textFieldR = new JTextField();
        textFieldR.setBounds(225, 70, 240, 34);
        configureModernInputDesign(textFieldR);
        formContainer.add(textFieldR);

        // 3. Admission Clock Metric Node
        JLabel lblTime = new JLabel("Check-In Timestamp");
        lblTime.setBounds(25, 125, 160, 20);
        lblTime.setFont(inputHeaderFont);
        lblTime.setForeground(elementLabelColor);
        formContainer.add(lblTime);

        textFieldINTIme = new JTextField();
        textFieldINTIme.setBounds(225, 120, 240, 34);
        configureModernInputDesign(textFieldINTIme);
        formContainer.add(textFieldINTIme);

        // 4. Ledger Deposits Node
        JLabel lblDeposit = new JLabel("Cleared Deposit (INR)");
        lblDeposit.setBounds(25, 175, 160, 20);
        lblDeposit.setFont(inputHeaderFont);
        lblDeposit.setForeground(elementLabelColor);
        formContainer.add(lblDeposit);

        textFieldAmount = new JTextField();
        textFieldAmount.setBounds(225, 170, 240, 34);
        configureModernInputDesign(textFieldAmount);
        formContainer.add(textFieldAmount);

        // 5. Unpaid Ledger Balances Node
        JLabel lblPending = new JLabel("Calculated Arrears Due");
        lblPending.setBounds(25, 225, 160, 20);
        lblPending.setFont(inputHeaderFont);
        lblPending.setForeground(elementLabelColor);
        formContainer.add(lblPending);

        textFieldPending = new JTextField();
        textFieldPending.setBounds(225, 220, 240, 34);
        textFieldPending.setBackground(new Color(255, 244, 244)); // Visual alert layout background
        textFieldPending.setForeground(new Color(225, 45, 45));    // Bright Red clear indicators
        textFieldPending.setFont(new Font("Segoe UI", Font.BOLD, 13));
        textFieldPending.setBorder(BorderFactory.createCompoundBorder(new LineBorder(new Color(254, 202, 202), 1), new EmptyBorder(0, 12, 0, 12)));
        textFieldPending.setEditable(false);
        formContainer.add(textFieldPending);

        // Form Call To Action Control Triggers
        JButton checkBtn = new JButton("Verify Ledger");
        checkBtn.setBounds(25, 290, 210, 42);
        applyActionControlBlueprint(checkBtn, new Color(12, 125, 150), Color.WHITE); // Medical Teal Accent Focus
        formContainer.add(checkBtn);

        JButton updateBtn = new JButton("Commit Sync");
        updateBtn.setBounds(255, 290, 210, 42);
        applyActionControlBlueprint(updateBtn, new Color(28, 38, 52), Color.WHITE); // Corporate Deep Blue Accent Focus
        formContainer.add(updateBtn);

        // Sidebar Branding Canvas Graphic Decoration Layer
        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/updated.png"));
        Image image = imageIcon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        JLabel panelHeroGraphic = new JLabel(new ImageIcon(image));
        panelHeroGraphic.setBounds(610, 95, 250, 250);
        panel.add(panelHeroGraphic);

        JButton backBtn = new JButton("Close Sub-Workspace");
        backBtn.setBounds(610, 395, 250, 42);
        applyActionControlBlueprint(backBtn, Color.WHITE, new Color(110, 125, 145));
        backBtn.setBorder(new LineBorder(new Color(210, 220, 232), 1));
        panel.add(backBtn);

        // --- CORE DATABASE SYSTEM CONNECTIVITY DRIVERS ---
        checkBtn.addActionListener(e -> {
            String targetSelectionName = (String) patientChoice.getSelectedItem();
            if (targetSelectionName == null) return;

            try {
                conn c = new conn();
                ResultSet patientDataset = c.statement.executeQuery("select * from Patient_Info where Name = '" + targetSelectionName + "'");
                while (patientDataset.next()) {
                    textFieldR.setText(patientDataset.getString("Room_Number"));
                    textFieldINTIme.setText(patientDataset.getString("Time"));
                    textFieldAmount.setText(patientDataset.getString("Deposite"));
                }

                ResultSet roomDataset = c.statement.executeQuery("select * from room where room_no = '" + textFieldR.getText() + "'");
                while (roomDataset.next()) {
                    String cost = roomDataset.getString("Price");
                    int balancesOutstanding = Integer.parseInt(cost) - Integer.parseInt(textFieldAmount.getText());
                    textFieldPending.setText(String.valueOf(balancesOutstanding));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        updateBtn.addActionListener(e -> {
            String targetedNameKey = (String) patientChoice.getSelectedItem();
            if (targetedNameKey == null) return;

            try {
                conn c = new conn();
                String queryUpdateParameters = "update Patient_Info set Room_Number = '" + textFieldR.getText() + 
                                               "', Time = '" + textFieldINTIme.getText() + 
                                               "', Deposite = '" + textFieldAmount.getText() + 
                                               "' where name = '" + targetedNameKey + "'";
                c.statement.executeUpdate(queryUpdateParameters);
                JOptionPane.showMessageDialog(null, "System Schema Restructured Successfully");
                setVisible(false);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        backBtn.addActionListener(e -> setVisible(false));

        // Layout boundaries structural compilation
        setUndecorated(true);
        setSize(950, 500);
        setLayout(null);
        setLocation(440, 250); // Balanced desktop resolution centering anchors
        setVisible(true);
    }

    // Custom utility method to apply design rules across inputs dynamically
    private void configureModernInputDesign(JTextField field) {
        Color baseNormalBorderColor = new Color(215, 224, 235);
        Color baseActiveBorderColor = new Color(14, 116, 144); // Glows cyan on select
        Color baseInputBackground = new Color(250, 252, 254);

        field.setBackground(baseInputBackground);
        field.setForeground(new Color(24, 40, 64));
        field.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        field.setBorder(BorderFactory.createCompoundBorder(new LineBorder(baseNormalBorderColor, 1), new EmptyBorder(0, 12, 0, 12)));

        field.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                field.setBorder(BorderFactory.createCompoundBorder(new LineBorder(baseActiveBorderColor, 1), new EmptyBorder(0, 12, 0, 12)));
                field.setBackground(Color.WHITE);
            }
            @Override
            public void focusLost(FocusEvent e) {
                field.setBorder(BorderFactory.createCompoundBorder(new LineBorder(baseNormalBorderColor, 1), new EmptyBorder(0, 12, 0, 12)));
                field.setBackground(baseInputBackground);
            }
        });
    }

    private void applyActionControlBlueprint(JButton btn, Color background, Color foreground) {
        btn.setBackground(background);
        btn.setForeground(foreground);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(null);
    }

    public static void main(String[] args) {
        new update_patient_details();
    }
}