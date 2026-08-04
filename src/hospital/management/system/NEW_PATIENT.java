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
    JTextField textFieldNumber, textName, textFieldDisease, textFieldDeposite, textFieldPhone, textFieldAddress;
    JRadioButton r1, r2;
    ButtonGroup genderGroup;
    Choice c1;
    JLabel date;
    JButton b1, b2;

    NEW_PATIENT() {
        // Main Window Frame Configuration (Height increased to 630 to fit new fields)
        setBounds(450, 150, 850, 630);
        getContentPane().setBackground(new Color(22, 27, 34)); // Cyber Dark inner frame
        setLayout(null);
        setUndecorated(true);

        // Core container panel
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 840, 620);
        panel.setBackground(new Color(27, 32, 40)); 
        panel.setBorder(new LineBorder(new Color(156, 39, 176, 120), 1));
        panel.setLayout(null);
        add(panel);

        // Form Header
        JLabel labelName = new JLabel("NEW PATIENT REGISTRATION");
        labelName.setBounds(50, 20, 400, 35);
        labelName.setFont(new Font("Segoe UI", Font.BOLD, 22));
        labelName.setForeground(new Color(230, 235, 245));
        panel.add(labelName);

        // Vector Artwork Setup
        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/patient.png"));
        Image image = imageIcon.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        JLabel label = new JLabel(new ImageIcon(image));
        label.setBounds(550, 180, 200, 200);
        panel.add(label);

        // Styling configurations
        Font labelFont = new Font("Segoe UI", Font.BOLD, 13);
        Color textMuted = new Color(160, 175, 190);
        Color inputBg = new Color(22, 27, 34);
        Color borderStroke = new Color(50, 62, 78);

        // 1. ID Dropdown
        JLabel labelID = new JLabel("Identity Document :");
        labelID.setBounds(50, 70, 160, 25);
        labelID.setFont(labelFont);
        labelID.setForeground(textMuted);
        panel.add(labelID);

        comboBox = new JComboBox<>(new String[] {"Aadhar Card", "Voter Id", "Driving License"});
        comboBox.setBounds(230, 70, 220, 28);
        comboBox.setBackground(inputBg);
        comboBox.setForeground(Color.white);
        comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        comboBox.setBorder(new LineBorder(borderStroke, 1));
        panel.add(comboBox);

        // 2. Document Number Field
        JLabel labelNumber = new JLabel("Document Number :");
        labelNumber.setBounds(50, 110, 160, 25);
        labelNumber.setFont(labelFont);
        labelNumber.setForeground(textMuted);
        panel.add(labelNumber);

        textFieldNumber = new JTextField();
        textFieldNumber.setBounds(230, 110, 220, 28);
        textFieldNumber.setBackground(inputBg);
        textFieldNumber.setForeground(Color.white);
        textFieldNumber.setCaretColor(Color.white);
        textFieldNumber.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        textFieldNumber.setBorder(new LineBorder(borderStroke, 1));
        panel.add(textFieldNumber);

        // 3. Patient Name Field
        JLabel labelName1 = new JLabel("Patient Name :");
        labelName1.setBounds(50, 150, 160, 25);
        labelName1.setFont(labelFont);
        labelName1.setForeground(textMuted);
        panel.add(labelName1);

        textName = new JTextField();
        textName.setBounds(230, 150, 220, 28);
        textName.setBackground(inputBg);
        textName.setForeground(Color.white);
        textName.setCaretColor(Color.white);
        textName.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        textName.setBorder(new LineBorder(borderStroke, 1));
        panel.add(textName);

        // 4. Gender Selection
        JLabel labelGender = new JLabel("Gender Selection :");
        labelGender.setBounds(50, 190, 160, 25);
        labelGender.setFont(labelFont);
        labelGender.setForeground(textMuted);
        panel.add(labelGender);

        r1 = new JRadioButton("Male");
        r1.setFont(new Font("Segoe UI", Font.BOLD, 13));
        r1.setForeground(Color.white);
        r1.setBackground(new Color(27, 32, 40));
        r1.setFocusPainted(false);
        r1.setBounds(230, 190, 80, 25);
        panel.add(r1);

        r2 = new JRadioButton("Female");
        r2.setFont(new Font("Segoe UI", Font.BOLD, 13));
        r2.setForeground(Color.white);
        r2.setBackground(new Color(27, 32, 40));
        r2.setFocusPainted(false);
        r2.setBounds(320, 190, 90, 25);
        panel.add(r2);

        genderGroup = new ButtonGroup();
        genderGroup.add(r1);
        genderGroup.add(r2);

        // 5. Phone Number Field (NEW)
        JLabel labelPhone = new JLabel("Phone Number :");
        labelPhone.setBounds(50, 230, 160, 25);
        labelPhone.setFont(labelFont);
        labelPhone.setForeground(textMuted);
        panel.add(labelPhone);

        textFieldPhone = new JTextField();
        textFieldPhone.setBounds(230, 230, 220, 28);
        textFieldPhone.setBackground(inputBg);
        textFieldPhone.setForeground(Color.white);
        textFieldPhone.setCaretColor(Color.white);
        textFieldPhone.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        textFieldPhone.setBorder(new LineBorder(borderStroke, 1));
        panel.add(textFieldPhone);

        // 6. Address Field (NEW)
        JLabel labelAddress = new JLabel("Patient Address :");
        labelAddress.setBounds(50, 270, 160, 25);
        labelAddress.setFont(labelFont);
        labelAddress.setForeground(textMuted);
        panel.add(labelAddress);

        textFieldAddress = new JTextField();
        textFieldAddress.setBounds(230, 270, 220, 28);
        textFieldAddress.setBackground(inputBg);
        textFieldAddress.setForeground(Color.white);
        textFieldAddress.setCaretColor(Color.white);
        textFieldAddress.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        textFieldAddress.setBorder(new LineBorder(borderStroke, 1));
        panel.add(textFieldAddress);

        // 7. Disease Diagnostics Input
        JLabel labelDisease = new JLabel("Diagnosis / Disease :");
        labelDisease.setBounds(50, 310, 160, 25);
        labelDisease.setFont(labelFont);
        labelDisease.setForeground(textMuted);
        panel.add(labelDisease);

        textFieldDisease = new JTextField();
        textFieldDisease.setBounds(230, 310, 220, 28);
        textFieldDisease.setBackground(inputBg);
        textFieldDisease.setForeground(Color.white);
        textFieldDisease.setCaretColor(Color.white);
        textFieldDisease.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        textFieldDisease.setBorder(new LineBorder(borderStroke, 1));
        panel.add(textFieldDisease);

        // 8. Room Selection Choice
        JLabel labelRoom = new JLabel("Assigned Room :");
        labelRoom.setBounds(50, 350, 160, 25);
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
        c1.setBounds(230, 350, 220, 28);
        c1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        c1.setForeground(Color.WHITE);
        c1.setBackground(inputBg);
        panel.add(c1);

        // 9. Check-in Timestamp
        JLabel labelDate = new JLabel("Check-in Time :");
        labelDate.setBounds(50, 390, 160, 25);
        labelDate.setFont(labelFont);
        labelDate.setForeground(textMuted);
        panel.add(labelDate);

        Date date1 = new Date();
        date = new JLabel("" + date1);
        date.setBounds(230, 390, 250, 25);
        date.setForeground(new Color(0, 229, 255));
        date.setFont(new Font("Segoe UI", Font.BOLD, 12));
        panel.add(date);

        // 10. Advance Deposit Input
        JLabel labelDeposite = new JLabel("Advance Deposit :");
        labelDeposite.setBounds(50, 430, 160, 25);
        labelDeposite.setFont(labelFont);
        labelDeposite.setForeground(textMuted);
        panel.add(labelDeposite);

        textFieldDeposite = new JTextField();
        textFieldDeposite.setBounds(230, 430, 220, 28);
        textFieldDeposite.setBackground(inputBg);
        textFieldDeposite.setForeground(Color.white);
        textFieldDeposite.setCaretColor(Color.white);
        textFieldDeposite.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        textFieldDeposite.setBorder(new LineBorder(borderStroke, 1));
        panel.add(textFieldDeposite);

        // Auto Price Loader
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

        // Action Buttons Setup
        b1 = new JButton("ADD RECORD");
        b1.setBounds(80, 520, 150, 36);
        b1.setFont(new Font("Segoe UI", Font.BOLD, 13));
        b1.setForeground(Color.WHITE);
        b1.setBackground(new Color(36, 44, 54));
        b1.setBorder(new LineBorder(new Color(0, 229, 255, 150), 1));
        b1.setFocusPainted(false);
        b1.addActionListener(this);
        panel.add(b1);

        b2 = new JButton("CANCEL");
        b2.setBounds(260, 520, 150, 36);
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
            String s9 = textFieldPhone.getText();    // Phone Number
            String s10 = textFieldAddress.getText(); // Address

            try {
                conn c = new conn();
                
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

                // Updated SQL statement including phone and address
                String q = "insert into Patient_Info values ('" + s1 + "', '" + s2 + "','" + s3 + "','" + s4 + "', '" + s5 + "', '" + s6 + "', '" + s7 + "', '" + s8 + "', '" + s9 + "', '" + s10 + "')";
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