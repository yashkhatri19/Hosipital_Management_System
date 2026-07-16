package hospital.management.system;

import net.proteanit.sql.DbUtils;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Room extends JFrame {

    JTable table;

    Room(){
        // Main Container Panel setup
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 890, 590);
        panel.setBackground(new Color(245, 247, 250)); // Modern Clean Slate Light Background
        panel.setLayout(null);
        add(panel);

        // Sidebar Decorative Image
        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/roomm.png"));
        Image image = imageIcon.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(image);
        JLabel label = new JLabel(imageIcon1);
        label.setBounds(640, 160, 200, 200);
        panel.add(label);

        // Table Component Creation
        table = new JTable();
        table.setRowHeight(35); // Big professional spacing like web interfaces
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setGridColor(new Color(230, 233, 238)); // Soft row lines
        table.setShowVerticalLines(false); // Clean flat appearance
        table.setBackground(Color.WHITE);
        table.setSelectionBackground(new Color(232, 240, 254));
        table.setSelectionForeground(Color.BLACK);

        // Styling the Table Header
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));
        header.setBackground(Color.WHITE);
        header.setForeground(new Color(70, 80, 95));
        header.setPreferredSize(new Dimension(100, 40));

        // Center Aligning Columns & Status Coloring
        DefaultTableCellRenderer customRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, 
                                                           boolean isSelected, boolean hasFocus, 
                                                           int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setHorizontalAlignment(JLabel.CENTER);
                
                // Add conditional text styling for availability
                if (column == 1 && value != null) {
                    String status = value.toString().trim();
                    if (status.equalsIgnoreCase("Available")) {
                        c.setForeground(new Color(46, 125, 50)); // Deep Dashboard Green
                        c.setFont(new Font("Segoe UI", Font.BOLD, 14));
                    } else {
                        c.setForeground(new Color(198, 40, 40)); // Deep Alert Red
                        c.setFont(new Font("Segoe UI", Font.BOLD, 14));
                    }
                } else {
                    c.setForeground(new Color(50, 50, 50));
                }
                return c;
            }
        };
        table.setDefaultRenderer(Object.class, customRenderer);

        // ScrollPane Container
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(25, 30, 580, 450);
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(218, 220, 224), 1));
        panel.add(scrollPane);

        // Fetching Database Content
        try {
            conn c = new conn();
            String q = "select room_no as 'Room No', Availability, Price, Room_Type as 'Bed Type' from room";
            ResultSet resultSet = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Modern flat styled Back Button
        JButton back = new JButton("Back to Dashboard");
        back.setBounds(25, 505, 180, 40);
        back.setFont(new Font("Segoe UI", Font.BOLD, 14));
        back.setBackground(new Color(38, 50, 56));
        back.setForeground(Color.WHITE);
        back.setFocusPainted(false);
        back.setBorder(BorderFactory.createEmptyBorder());
        panel.add(back);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        // Frame Window Parameters
        setUndecorated(true);
        setSize(900, 600);
        setLayout(null);
        setLocation(300, 230);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Room();
    }
}