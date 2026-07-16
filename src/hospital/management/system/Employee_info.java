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

public class Employee_info extends JFrame {
    Employee_info() {
        // Main window outer frame properties alignment with the core system dashboard
        setBounds(415, 200, 1000, 600);
        getContentPane().setBackground(new Color(22, 27, 34)); // Dark Cyber space layout
        setLayout(null);
        setUndecorated(true);

        // Visual container frame panel setup
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 990, 590);
        panel.setBackground(new Color(27, 32, 40)); 
        panel.setBorder(new LineBorder(new Color(156, 39, 176, 120), 1)); // Neon purple edge accent
        panel.setLayout(null);
        add(panel);

        // Window section main title
        JLabel sectionTitle = new JLabel("EMPLOYEE DIRECTORY WORKSPACE");
        sectionTitle.setBounds(35, 20, 400, 30);
        sectionTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        sectionTitle.setForeground(new Color(230, 235, 245));
        panel.add(sectionTitle);

        // Advanced Dark Styled JTable Configuration
        JTable table = new JTable();
        table.setBackground(new Color(22, 27, 34));
        table.setForeground(new Color(200, 210, 230));
        table.setSelectionBackground(new Color(40, 50, 65));
        table.setSelectionForeground(Color.WHITE);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.setRowHeight(35); // Native padding spaces between entries
        table.setShowGrid(false); // Drop raw retro vertical cell lines
        table.setIntercellSpacing(new Dimension(0, 0));

        // High-fidelity Flat Table Header Design engine 
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));
        header.setBackground(new Color(36, 44, 54));
        header.setForeground(new Color(160, 175, 190));
        header.setBorder(new LineBorder(new Color(50, 62, 78), 1));

        // Loading standard operational records from database
        try {
            conn c = new conn();
            String q = "select * from EMP_INFO";
            ResultSet resultSet = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Native padding and alignment mapping inside table cells
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.LEFT);
        cellRenderer.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));
        table.setDefaultRenderer(Object.class, cellRenderer);

        // Wrap inside custom scroll mechanism to handle massive dataset overflow securely
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(35, 75, 920, 410);
        scrollPane.setBackground(new Color(22, 27, 34));
        scrollPane.getViewport().setBackground(new Color(22, 27, 34));
        scrollPane.setBorder(new LineBorder(new Color(50, 62, 78), 1));
        panel.add(scrollPane);

        // Sleek navigation controller close button
        JButton button = new JButton("CLOSE PANEL");
        button.setBounds(805, 515, 150, 38);
        button.setFont(new Font("Segoe UI", Font.BOLD, 13));
        button.setForeground(new Color(244, 67, 54)); // Red alert identity color
        button.setBackground(new Color(36, 44, 54));
        button.setBorder(new LineBorder(new Color(244, 67, 54, 150), 1));
        button.setFocusPainted(false);
        panel.add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new Employee_info();
    }
}