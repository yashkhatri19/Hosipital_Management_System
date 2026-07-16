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

public class ALL_Patient_Info extends JFrame {
    ALL_Patient_Info() {
        // Main structural layout constraints matching system dashboard bounds
        setBounds(465, 200, 900, 600);
        getContentPane().setBackground(new Color(22, 27, 34)); // Matte Cyber Dark background
        setLayout(null);
        setUndecorated(true);

        // Core containment wrapper panel configuration
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 890, 590);
        panel.setBackground(new Color(27, 32, 40)); 
        panel.setBorder(new LineBorder(new Color(156, 39, 176, 120), 1)); // Cyber purple perimeter border
        panel.setLayout(null);
        add(panel);

        // Frame header label section
        JLabel sectionTitle = new JLabel("PATIENT DATABASE REGISTRY");
        sectionTitle.setBounds(35, 20, 400, 30);
        sectionTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        sectionTitle.setForeground(new Color(230, 235, 245));
        panel.add(sectionTitle);

        // Modern flat design JTable instance setup
        JTable table = new JTable();
        table.setBackground(new Color(22, 27, 34));
        table.setForeground(new Color(200, 210, 230));
        table.setSelectionBackground(new Color(40, 50, 65));
        table.setSelectionForeground(Color.WHITE);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.setRowHeight(35); // Roomy spacing for clean visibility
        table.setShowGrid(false); // Drop standard legacy grid lines
        table.setIntercellSpacing(new Dimension(0, 0));

        // Styling the table column headers globally via JTableHeader
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));
        header.setBackground(new Color(36, 44, 54));
        header.setForeground(new Color(160, 175, 190));
        header.setBorder(new LineBorder(new Color(50, 62, 78), 1));

        // Loading standard system records safely from database
        try {
            conn c = new conn();
            String q = "select * from Patient_Info";
            ResultSet resultSet = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Custom renderer for handling text alignments and dynamic cell text padding
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.LEFT);
        cellRenderer.setBorder(BorderFactory.createEmptyBorder(0, 12, 0, 0));
        table.setDefaultRenderer(Object.class, cellRenderer);

        // ScrollPane encapsulation layer to secure structural row overflows
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(35, 75, 820, 410);
        scrollPane.setBackground(new Color(22, 27, 34));
        scrollPane.getViewport().setBackground(new Color(22, 27, 34));
        scrollPane.setBorder(new LineBorder(new Color(50, 62, 78), 1));
        panel.add(scrollPane);

        // Dynamic flat style back navigation button control
        JButton button = new JButton("CLOSE VIEW");
        button.setBounds(705, 515, 150, 38);
        button.setFont(new Font("Segoe UI", Font.BOLD, 13));
        button.setForeground(new Color(244, 67, 54)); // Crimson alert theme text color
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
        new ALL_Patient_Info();
    }
}