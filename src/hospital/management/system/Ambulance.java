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

public class Ambulance extends JFrame {
    
    public Ambulance() {
        // Main structural layout canvas (Deep Steel Blue Theme from screen reference)
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 900, 600);
        panel.setBackground(new Color(24, 53, 79)); // Osler 360 Deep Steel Blue Background
        panel.setBorder(new LineBorder(new Color(38, 77, 112), 1));
        panel.setLayout(null);
        add(panel);

        // Header Section Branding Title
        JLabel titleLabel = new JLabel("OSLER 360  >  AMBULANCE METRICS DISPATCH");
        titleLabel.setBounds(30, 25, 600, 25);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(new Color(126, 200, 255)); // Bright Sky Blue Accent
        panel.add(titleLabel);

        // Core Interactive View Component Setup
        JTable table = new JTable();
        table.setBackground(new Color(29, 63, 94)); // Tinted surface layout for records
        table.setForeground(Color.WHITE);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.setRowHeight(32);
        table.setGridColor(new Color(38, 77, 112));
        table.setSelectionBackground(new Color(126, 200, 255));
        table.setSelectionForeground(new Color(24, 53, 79));

        // Embedding dynamic database records
        try {
            conn c = new conn();
            String q = "select * from Ambulance";
            ResultSet resultSet = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Custom Layout Config for JTable Headers to align with UI requirements
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));
        header.setBackground(new Color(38, 77, 112)); // Distinct solid header layout container
        header.setForeground(new Color(200, 230, 255));
        
        // Centering alignment for smooth modern visualization
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);

        // Wrapping inside modern layout pane structure
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 80, 840, 420);
        scrollPane.setBackground(new Color(24, 53, 79));
        scrollPane.setBorder(new LineBorder(new Color(38, 77, 112), 1));
        scrollPane.getViewport().setBackground(new Color(29, 63, 94));
        panel.add(scrollPane);

        // --- BUTTON CONTROL INTERFACE ---
        JButton button = new JButton("RETURN TO CORE");
        button.setBounds(365, 525, 170, 38);
        button.setBackground(new Color(126, 200, 255)); // Light Accent Blue Button matching Osler 360 card login
        button.setForeground(new Color(24, 53, 79));   // Deep contrast text matrix
        button.setFont(new Font("Segoe UI", Font.BOLD, 13));
        button.setFocusPainted(false);
        button.setBorder(null);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        // Frame Parametric Directives
        setUndecorated(true);
        setSize(900, 600);
        setLayout(null);
        setLocation(300, 200);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Ambulance();
    }
}