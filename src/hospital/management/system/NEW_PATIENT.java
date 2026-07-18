package hospital.management.system;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.util.Date;

public class NEW_PATIENT extends JFrame implements ActionListener {
    JComboBox<String> comboBox;
    JTextField textFieldNumber, textName, textFieldDisease, textFieldDeposite;
    JRadioButton r1, r2;
    ButtonGroup genderGroup;
    Choice c1;
    JLabel date;
    JButton b1, b2;

    NEW_PATIENT() {
        // Main Window Frame Configuration
        setBounds(530, 220, 850, 550);
        getContentPane().setBackground(new Color(22, 27, 34)); // Cyber Dark inner frame
        setLayout(null);
        setUndecorated(true);

        // Core container panel with dynamic neon glow accent border
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 840, 540);
        panel.setBackground(new Color(27, 32, 40)); 
        panel.setBorder(new LineBorder(new Color(156, 39, 176, 120), 1)); // Soft purple cyber border
        panel.setLayout(null);
        add(panel);

        // Form Header Customization
        JLabel labelName = new JLabel("NEW PATIENT REGISTRATION");
        labelName.setBounds(50, 25, 400, 40);
        labelName.setFont(new Font("Segoe UI", Font.BOLD, 22));
        labelName.setForeground(new Color(230, 235, 245));
        panel.add(labelName);

        // Right side Vector Artwork Setup
        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/patient.png"));
        Image image = imageIcon.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        JLabel label = new JLabel(new ImageIcon(image));
        label.setBounds(540, 150, 200, 200);
        panel.add(label);

        // Form Fields Styling configurations
        Font labelFont = new Font("Segoe UI", Font.BOLD, 14);
        Color textMuted = new Color(160, 175, 190);
        Color inputBg = new Color(22, 27, 34);
        Color borderStroke = new Color(50, 62, 78);

        // ID Dropdown
        JLabel labelID = new JLabel("Identity Document :");
        labelID.setBounds(50, 90, 160, 25);
        labelID.setFont(labelFont);
        labelID.setForeground(textMuted);
        panel.add(labelID);

        comboBox = new JComboBox<>(new String[] {"Aadhar Card", "Voter Id", "Driving License"});
        comboBox.setBounds(230, 90, 220, 28);
        comboBox.setBackground(inputBg);
        comboBox.setForeground(Color.white);
        comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        comboBox.setBorder(new LineBorder(borderStroke, 1));
        panel.add(comboBox);

        // Document Number Field
        JLabel labelNumber = new JLabel("Document Number :");
        labelNumber.setBounds(50, 135, 160, 25);
        labelNumber.setFont(labelFont);
        labelNumber.setForeground(textMuted);
        panel.add(labelNumber);

        textFieldNumber = new JTextField();
        textFieldNumber.setBounds(230, 135, 220, 28);
        textFieldNumber.setBackground(inputBg);
        textFieldNumber.setForeground(Color.white);
        textFieldNumber.setCaretColor(Color.white);
        textFieldNumber.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textFieldNumber.setBorder(new LineBorder(borderStroke, 1));
        panel.add(textFieldNumber);

        // Patient Name Field
        JLabel labelName1 = new JLabel("Patient Name :");
        labelName1.setBounds(50, 180, 160, 25);
        labelName1.setFont(labelFont);
        labelName1.setForeground(textMuted);
        panel.add(labelName1);

        textName = new JTextField();
        textName.setBounds(230, 180, 220, 28);
        textName.setBackground(inputBg);
        textName.setForeground(Color.white);
        textName.setCaretColor(Color.white);
        textName.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textName.setBorder(new LineBorder(borderStroke, 1));
        panel.add(textName);

        // Gender Choice Buttons
        JLabel labelGender = new JLabel("Gender Selection :");
        labelGender.setBounds(50, 225, 160, 25);
        labelGender.setFont(labelFont);
        labelGender.setForeground(textMuted);
        panel.add(labelGender);

        r1 = new JRadioButton("Male");
        r1.setFont(new Font("Segoe UI", Font.BOLD, 14));
        r1.setForeground(Color.white);
        r1.setBackground(new Color(27, 32, 40));
        r1.setFocusPainted(false);
        r1.setBounds(230, 225, 80, 25);
        panel.add(r1);

        r2 = new JRadioButton("Female");
        r2.setFont(new Font("Segoe UI", Font.BOLD, 14));
        r2.setForeground(Color.white);
        r2.setBackground(new Color(27, 32, 40));
        r2.setFocusPainted(false);
        r2.setBounds(320, 225, 90, 25);
        panel.add(r2);

        genderGroup = new ButtonGroup();
        genderGroup.add(r1);
        genderGroup.add(r2);

        // Disease Diagnostics Input
        JLabel labelDisease = new JLabel("Diagnosis / Disease :");
        labelDisease.setBounds(50, 270, 160, 25);
        labelDisease.setFont(labelFont);
        labelDisease.setForeground(textMuted);
        panel.add(labelDisease);
        // Disease input field with dynamic styling
        textFieldDisease = new JTextField();
        textFieldDisease.setBounds(230, 270, 220, 28);
        textFieldDisease.setBackground(inputBg);
        textFieldDisease.setForeground(Color.white);
        textFieldDisease.setCaretColor(Color.white);
        textFieldDisease.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textFieldDisease.setBorder(new LineBorder(borderStroke, 1));
        panel.add(textFieldDisease);

        // Room Selection Choice
        JLabel labelRoom = new JLabel("Assigned Room :");
        labelRoom.setBounds(50, 315, 160, 25);
        labelRoom.setFont(labelFont);
        labelRoom.setForeground(textMuted);
        panel.add(labelRoom);

        c1 = new Choice();
        try {
            conn c = new conn();
            ResultSet resultSet = c.statement.executeQuery("select * from Room where Availability = 'Available'");
            while (resultSet.next()) {
                c1.add(resultSet.getString("room_no"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        c1.setBounds(230, 315, 220, 28);
        c1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        c1.setForeground(Color.WHITE);
        c1.setBackground(inputBg);
        panel.add(c1);

        // System Generated Timestamp
        JLabel labelDate = new JLabel("Check-in Time :");
        labelDate.setBounds(50, 360, 160, 25);
        labelDate.setFont(labelFont);
        labelDate.setForeground(textMuted);
        panel.add(labelDate);

        Date date1 = new Date();
        date = new JLabel("" + date1);
        date.setBounds(230, 360, 250, 25);
        date.setForeground(new Color(0, 229, 255)); // Tech Cyan dynamic color accent
        date.setFont(new Font("Segoe UI", Font.BOLD, 13));
        panel.add(date);

        // Advanced Deposit input
        JLabel labelDeposite = new JLabel("Advance Deposit :");
        labelDeposite.setBounds(50, 405, 160, 25);
        labelDeposite.setFont(labelFont);
        labelDeposite.setForeground(textMuted);
        panel.add(labelDeposite);

        textFieldDeposite = new JTextField();
        textFieldDeposite.setBounds(230, 405, 220, 28);
        textFieldDeposite.setBackground(inputBg);
        textFieldDeposite.setForeground(Color.white);
        textFieldDeposite.setCaretColor(Color.white);
        textFieldDeposite.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textFieldDeposite.setBorder(new LineBorder(borderStroke, 1));
        panel.add(textFieldDeposite);

        // --- DYNAMIC AUTO PRICE LOAD ON DROP-DOWN CHANGE ---
        try {
            conn c = new conn();
            ResultSet rs = c.statement.executeQuery("select Price from Room where room_no = '" + c1.getSelectedItem() + "'");
            if (rs.next()) {
                textFieldDeposite.setText(rs.getString("Price"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        c1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try {
                    conn c = new conn();
                    ResultSet rs = c.statement.executeQuery("select Price from Room where room_no = '" + c1.getSelectedItem() + "'");
                    if (rs.next()) {
                        textFieldDeposite.setText(rs.getString("Price"));
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        // ----------------------------------------------------

        // Action Buttons Setup
        b1 = new JButton("ADD RECORD");
        b1.setBounds(80, 475, 150, 36);
        b1.setFont(new Font("Segoe UI", Font.BOLD, 13));
        b1.setForeground(Color.WHITE);
        b1.setBackground(new Color(36, 44, 54));
        b1.setBorder(new LineBorder(new Color(0, 229, 255, 150), 1));
        b1.setFocusPainted(false);
        b1.addActionListener(this);
        panel.add(b1);

        b2 = new JButton("CANCEL");
        b2.setBounds(260, 475, 150, 36);
        b2.setFont(new Font("Segoe UI", Font.BOLD, 13));
        b2.setForeground(new Color(244, 67, 54));
        b2.setBackground(new Color(36, 44, 54));
        b2.setBorder(new LineBorder(new Color(244, 67, 54, 150), 1));
        b2.setFocusPainted(false);
        b2.addActionListener(this);
        panel.add(b2);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            String radioBTN = null;
            if (r1.isSelected()) {
                radioBTN = "Male";
            } else if (r2.isSelected()) {
                radioBTN = "Female";
            }
            String s1 = (String) comboBox.getSelectedItem();
            String s2 = textFieldNumber.getText();
            String s3 = textName.getText();
            String s4 = radioBTN;
            String s5 = textFieldDisease.getText();
            String s6 = c1.getSelectedItem();
            String s7 = date.getText();
            String s8 = textFieldDeposite.getText();

            try {
                conn c = new conn();
                
                // Pure validation checking algorithm
                int enteredDeposit = Integer.parseInt(s8);
                int actualRoomRent = 0;
                ResultSet rs = c.statement.executeQuery("select Price from Room where room_no = '" + s6 + "'");
                if (rs.next()) {
                    actualRoomRent = Integer.parseInt(rs.getString("Price"));
                }

                if (enteredDeposit > actualRoomRent) {
                    JOptionPane.showMessageDialog(null, 
                        "Error: Deposit amount (" + enteredDeposit + ") cannot exceed Room Rent (" + actualRoomRent + ")!", 
                        "Validation Error", 
                        JOptionPane.ERROR_MESSAGE);
                    return; 
                }

                // Agar custom logic transparent hai toh statements fire karein
                String q = "insert into Patient_Info values ('" + s1 + "', '" + s2 + "','" + s3 + "','" + s4 + "', '" + s5 + "', '" + s6 + "', '" + s7 + "', '" + s8 + "')";
                String q1 = "update room set Availability = 'Occupied' where room_no = '" + s6 + "'";
                
                c.statement.executeUpdate(q);
                c.statement.executeUpdate(q1);
                
                JOptionPane.showMessageDialog(null, "Patient Record Added Successfully");
                setVisible(false);
            } catch (Exception E) {
                E.printStackTrace();
                JOptionPane.showMessageDialog(null, "Database Error: " + E.getMessage());
            }
        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new NEW_PATIENT();
    }
}