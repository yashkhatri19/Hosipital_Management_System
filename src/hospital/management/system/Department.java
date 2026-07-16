package hospital.management.system;

import net.proteanit.sql.DbUtils;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Department extends JFrame {
    Department() {
        // Main Window Frame positioning inside the central dashboard area
        setBounds(565, 230, 700, 500);
        getContentPane().setBackground(new Color(22, 27, 34)); // Dark Cyber background
        setLayout(null);
        setUndecorated(true);

        // Core visual containment wrapper panel
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 690, 490);
        panel.setBackground(new Color(27, 32, 40)); 
        panel.setBorder(new LineBorder(new Color(156, 39, 176, 120), 1)); // Soft neon purple border
        panel.setLayout(null);
        add(panel);

        // Title Header styling
        JLabel sectionTitle = new JLabel("DEPARTMENT DIRECTORY");
        sectionTitle.setBounds(35, 20, 300, 30);
        sectionTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        sectionTitle.setForeground(new Color(230, 235, 245));
        panel.add(sectionTitle);

        // Custom Grid table configurations
        JTable table = new JTable();
        table.setBackground(new Color(22, 27, 34));
        table.setForeground(new Color(200, 210, 230));
        table.setSelectionBackground(new Color(40, 50, 65));
        table.setSelectionForeground(Color.WHITE);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setRowHeight(35); // Clean vertical padding spaces
        table.setShowGrid(false); // Remove standard rough lines
        table.setIntercellSpacing(new Dimension(0, 0));

        // Modern flat table headers rendering engine customization
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));
        header.setBackground(new Color(36, 44, 54));
        header.setForeground(new Color(160, 175, 190));
        header.setBorder(new LineBorder(new Color(50, 62, 78), 1));

        // Fetching structural database records
        try {
            conn c = new conn();
            String q = "select * from department";
            ResultSet resultSet = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Aligning rows data smoothly to center-left space bounds
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(JLabel.LEFT);
        leftRenderer.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));
        table.setDefaultRenderer(Object.class, leftRenderer);

        // Embedding inside a styled dark custom scroll viewpane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(35, 75, 620, 310);
        scrollPane.setBackground(new Color(22, 27, 34));
        scrollPane.getViewport().setBackground(new Color(22, 27, 34));
        scrollPane.setBorder(new LineBorder(new Color(50, 62, 78), 1));
        panel.add(scrollPane);

        // Modern stylized navigation back button structure
        JButton b1 = new JButton("CLOSE VIEW");
        b1.setBounds(505, 415, 150, 38);
        b1.setFont(new Font("Segoe UI", Font.BOLD, 13));
        b1.setForeground(new Color(244, 67, 54)); // Alert theme text color
        b1.setBackground(new Color(36, 44, 54));
        b1.setBorder(new LineBorder(new Color(244, 67, 54, 150), 1));
        b1.setFocusPainted(false);
        panel.add(b1);

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new Department();
    }
}