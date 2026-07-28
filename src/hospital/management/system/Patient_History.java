package hospital.management.system;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;

public class Patient_History extends JFrame {

    public Patient_History() {
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 800, 450);
        panel.setBackground(new Color(20, 24, 38));
        panel.setBorder(new LineBorder(new Color(36, 44, 68), 1));
        panel.setLayout(null);
        add(panel);

        // Branding
        JLabel label = new JLabel("ARCHIVES  >  DISCHARGE HISTORY");
        label.setBounds(40, 25, 500, 22);
        label.setFont(new Font("Segoe UI", Font.BOLD, 18));
        label.setForeground(new Color(0, 240, 255));
        panel.add(label);

        // Table Setup
        String[] columns = {"Patient Name", "Room No", "In-Time", "Out-Time"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);
        
        table.setBackground(new Color(27, 33, 53));
        table.setForeground(Color.WHITE);
        table.setGridColor(new Color(42, 53, 84));
        table.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        table.setRowHeight(25);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        table.getTableHeader().setBackground(new Color(36, 44, 68));
        table.getTableHeader().setForeground(new Color(0, 240, 255));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(40, 70, 720, 300);
        scrollPane.getViewport().setBackground(new Color(20, 24, 38));
        scrollPane.setBorder(new LineBorder(new Color(42, 53, 84), 1));
        panel.add(scrollPane);

        // Fetch History from Database
        try {
            conn c = new conn();
            ResultSet rs = c.statement.executeQuery("select * from Discharge_History");
            while (rs.next()) {
                String name = rs.getString("patient_name");
                String room = rs.getString("room_number");
                String inTime = rs.getString("in_time");
                String outTime = rs.getString("out_time");
                model.addRow(new Object[]{name, room, inTime, outTime});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Close Button
        JButton backBtn = new JButton("CLOSE");
        backBtn.setBounds(330, 385, 140, 35);
        backBtn.setBackground(new Color(55, 65, 81));
        backBtn.setForeground(Color.WHITE);
        backBtn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        backBtn.setFocusPainted(false);
        backBtn.setBorder(null);
        backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backBtn.addActionListener(e -> setVisible(false));
        panel.add(backBtn);

        setUndecorated(true);
        setSize(800, 450);
        setLayout(null);
        setLocation(470, 240);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Patient_History();
    }
}