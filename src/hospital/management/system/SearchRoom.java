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

public class SearchRoom extends JFrame {

    JComboBox<String> statusCombo;
    JTable table;

    public SearchRoom() {
        // Main glassmorphic styled pop-up panel container
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 750, 520);
        panel.setBackground(new Color(22, 27, 34)); // Match with the core dashboard dark panel
        panel.setBorder(new LineBorder(new Color(50, 62, 78), 1));
        panel.setLayout(null);
        add(panel);

        // Section Dashboard Icon/Label Module
        JLabel formTitle = new JLabel("ROOM SEARCH DESK");
        formTitle.setBounds(40, 25, 300, 30);
        formTitle.setForeground(new Color(230, 235, 245));
        formTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        panel.add(formTitle);

        JLabel formSub = new JLabel("Filter live room availability status records");
        formSub.setBounds(40, 50, 400, 20);
        formSub.setForeground(new Color(110, 125, 140));
        formSub.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        panel.add(formSub);

        // Modern Controls Panel (Top Bar inside popup)
        JPanel filterArea = new JPanel();
        filterArea.setBounds(40, 90, 670, 60);
        filterArea.setBackground(new Color(27, 32, 40));
        filterArea.setBorder(new LineBorder(new Color(40, 50, 65), 1));
        filterArea.setLayout(null);
        panel.add(filterArea);

        JLabel statusLabel = new JLabel("Room Status :");
        statusLabel.setBounds(20, 20, 100, 20);
        statusLabel.setForeground(new Color(200, 210, 230));
        statusLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        filterArea.add(statusLabel);

        // Replaced old Choice drop-down with a sleek modern JComboBox
        String[] options = {"Available", "Occupied"};
        statusCombo = new JComboBox<>(options);
        statusCombo.setBounds(130, 16, 150, 28);
        statusCombo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        statusCombo.setBackground(new Color(36, 44, 54));
        statusCombo.setForeground(Color.WHITE);
        statusCombo.setBorder(new LineBorder(new Color(50, 62, 78), 1));
        filterArea.add(statusCombo);

        // Table container wrapper setup for clean scrolling feel
        table = new JTable();
        table.setBackground(new Color(27, 32, 40));
        table.setForeground(new Color(220, 225, 235));
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.setRowHeight(30);
        table.setGridColor(new Color(36, 44, 54));
        table.setSelectionBackground(new Color(40, 50, 65));
        table.setSelectionForeground(Color.WHITE);

        // Stylizing Header layout parameters
        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(36, 44, 54));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));
        header.setBorder(new LineBorder(new Color(50, 62, 78), 1));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(40, 175, 670, 240);
        scrollPane.setBackground(new Color(27, 32, 40));
        scrollPane.getViewport().setBackground(new Color(27, 32, 40));
        scrollPane.setBorder(new LineBorder(new Color(40, 50, 65), 1));
        panel.add(scrollPane);

        // Auto Load Initial Records Data
        loadTableData("select * from room");

        // Action Trigger Configuration - Custom Search Action Button
        JButton searchBtn = new JButton("Run Search Query");
        searchBtn.setBounds(40, 445, 180, 40);
        searchBtn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        searchBtn.setFocusPainted(false);
        searchBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        searchBtn.setBackground(new Color(36, 120, 240)); // High-Contrast Action Blue
        searchBtn.setForeground(Color.WHITE);
        searchBtn.setBorder(null);
        panel.add(searchBtn);

        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String q = "select * from Room where Availability = '" + statusCombo.getSelectedItem() + "'";
                loadTableData(q);
            }
        });

        // Close Pop-up Overlay Anchor Action Button
        JButton backBtn = new JButton("Dismiss View");
        backBtn.setBounds(530, 445, 180, 40);
        backBtn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        backBtn.setFocusPainted(false);
        backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backBtn.setBackground(new Color(36, 44, 54));
        backBtn.setForeground(new Color(200, 210, 230));
        backBtn.setBorder(new LineBorder(new Color(50, 62, 78), 1));
        panel.add(backBtn);

        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        // Frame Layout parameters configurations
        setUndecorated(true);
        setSize(750, 520);
        setLayout(null);
        setLocation(530, 220); // Perfectly centered alignment adjustments inside the dashboard main display card
        setVisible(true);
    }
    // Load Table Data Method with Centered Text Alignment for Professional Feel
    private void loadTableData(String query) {
        try {
            conn c = new conn();
            ResultSet resultSet = c.statement.executeQuery(query);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
            
            // Text alignment inside columns to center for professional feel
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            for(int i = 0; i < table.getColumnCount(); i++){
                table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new SearchRoom();
    }
}