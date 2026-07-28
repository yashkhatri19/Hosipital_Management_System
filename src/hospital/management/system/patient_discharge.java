package hospital.management.system;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

public class patient_discharge extends JFrame {
    
    private Choice nameChoice;
    private JLabel RNo;
    private JLabel INTime;

    public patient_discharge() {
        // Main Canvas
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 800, 400);
        panel.setBackground(new Color(20, 24, 38));
        panel.setBorder(new LineBorder(new Color(36, 44, 68), 1));
        panel.setLayout(null);
        add(panel);

        // Header Title
        JLabel label = new JLabel("COMMAND CENTER  >  PATIENT DISCHARGE");
        label.setBounds(40, 30, 500, 22);
        label.setFont(new Font("Segoe UI", Font.BOLD, 18));
        label.setForeground(new Color(0, 240, 255));
        panel.add(label);

        JLabel labelSub = new JLabel("Archive patient records and reset room allocations.");
        labelSub.setBounds(40, 55, 500, 15);
        labelSub.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        labelSub.setForeground(new Color(120, 132, 168));
        panel.add(labelSub);

        // Container Card
        JPanel cardContainer = new JPanel();
        cardContainer.setBounds(40, 95, 720, 265); 
        cardContainer.setBackground(new Color(27, 33, 53));
        cardContainer.setBorder(new LineBorder(new Color(42, 53, 84), 1));
        cardContainer.setLayout(null);
        panel.add(cardContainer);

        Font labelFont = new Font("Segoe UI", Font.BOLD, 13);
        Color mutedTextColor = new Color(165, 180, 215);
        Color highlightValueColor = new Color(255, 255, 255);

        // Patient Selector
        JLabel label2 = new JLabel("Patient Name");
        label2.setBounds(40, 25, 160, 20); 
        label2.setFont(labelFont);
        label2.setForeground(mutedTextColor);
        cardContainer.add(label2);

        nameChoice = new Choice();
        nameChoice.setBounds(240, 22, 440, 25); 
        nameChoice.setBackground(new Color(36, 44, 68));
        nameChoice.setForeground(Color.WHITE);
        nameChoice.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        cardContainer.add(nameChoice);

        try {
            conn c = new conn();
            ResultSet resultSet = c.statement.executeQuery("select Name from Patient_Info");
            while (resultSet.next()) {
                nameChoice.add(resultSet.getString("Name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Room Field
        JLabel label3 = new JLabel("Assigned Room");
        label3.setBounds(40, 70, 160, 20);
        label3.setFont(labelFont);
        label3.setForeground(mutedTextColor);
        cardContainer.add(label3);

        RNo = new JLabel("---");
        RNo.setBounds(240, 70, 440, 20);
        RNo.setFont(new Font("Segoe UI", Font.BOLD, 14));
        RNo.setForeground(new Color(168, 85, 247));
        cardContainer.add(RNo);

        // In-Time Field
        JLabel label4 = new JLabel("System In-Time");
        label4.setBounds(40, 115, 160, 20);
        label4.setFont(labelFont);
        label4.setForeground(mutedTextColor);
        cardContainer.add(label4);

        INTime = new JLabel("---");
        INTime.setBounds(240, 115, 440, 20);
        INTime.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        INTime.setForeground(highlightValueColor);
        cardContainer.add(INTime);

        // Out-Time Field
        JLabel label5 = new JLabel("Telemetry Out-Time");
        label5.setBounds(40, 160, 160, 20);
        label5.setFont(labelFont);
        label5.setForeground(mutedTextColor);
        cardContainer.add(label5);

        Date date = new Date();
        JLabel OUTTime = new JLabel("" + date);
        OUTTime.setBounds(240, 160, 440, 20);
        OUTTime.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        OUTTime.setForeground(highlightValueColor);
        cardContainer.add(OUTTime);

        // Buttons
        JButton Check = new JButton("CHECK");
        Check.setBounds(240, 210, 130, 36);
        applyCyberStyleButton(Check, new Color(13, 148, 136), Color.WHITE); 
        cardContainer.add(Check);

        JButton discharge = new JButton("DISCHARGE");
        discharge.setBounds(395, 210, 130, 36);
        applyCyberStyleButton(discharge, new Color(220, 38, 38), Color.WHITE); 
        cardContainer.add(discharge);

        JButton Back = new JButton("CANCEL");
        Back.setBounds(550, 210, 130, 36);
        applyCyberStyleButton(Back, new Color(55, 65, 81), new Color(209, 213, 219)); 
        cardContainer.add(Back);

        // Action Handlers
        Check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    conn c = new conn();
                    ResultSet resultSet = c.statement.executeQuery("select * from Patient_Info where Name = '" + nameChoice.getSelectedItem() + "'");
                    while (resultSet.next()) {
                        RNo.setText(resultSet.getString("Room_Number"));
                        INTime.setText(resultSet.getString("Time"));
                    }
                } catch (Exception E) {
                    E.printStackTrace();
                }
            }
        });

        discharge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    conn c = new conn();
                    String name = nameChoice.getSelectedItem();
                    String room = RNo.getText();
                    String inTime = INTime.getText();
                    String outTime = OUTTime.getText();

                    // 1. Archive to Discharge_History
                    String archiveQuery = "insert into Discharge_History values('" + name + "', '" + room + "', '" + inTime + "', '" + outTime + "')";
                    c.statement.executeUpdate(archiveQuery);

                    // 2. Remove from active patients
                    c.statement.executeUpdate("delete from Patient_Info where Name = '" + name + "'");

                    // 3. Mark room as available
                    c.statement.executeUpdate("update room set Availability = 'Available' where room_no = '" + room + "'");

                    JOptionPane.showMessageDialog(null, "Patient Archived & Discharged Successfully!");
                    setVisible(false);
                } catch (Exception E) {
                    E.printStackTrace();
                }
            }
        });

        Back.addActionListener(e -> setVisible(false));

        setUndecorated(true);
        setSize(800, 400);
        setLayout(null);
        setLocation(470, 260);
        setVisible(true);
    }

    private void applyCyberStyleButton(JButton btn, Color bg, Color fg) {
        btn.setBackground(bg);
        btn.setForeground(fg);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setFocusPainted(false);
        btn.setBorder(null);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public static void main(String[] args) {
        new patient_discharge();
    }
}