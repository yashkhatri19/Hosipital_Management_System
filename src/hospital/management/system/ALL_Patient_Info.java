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
        // Main bounds matching the workspace setup
        setBounds(465, 200, 900, 600);
        getContentPane().setBackground(new Color(245, 248, 250)); // Soft clean white/light-blue tinted background
        setLayout(null);
        setUndecorated(true);

        // Core container panel matching the light minimalist look
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 890, 590);
        panel.setBackground(Color.WHITE); // Pure white card surface
        panel.setBorder(new LineBorder(new Color(225, 232, 240), 1)); // Very soft gray boundary line
        panel.setLayout(null);
        add(panel);

        // Header Section with dark bold elegant text
        JLabel sectionTitle = new JLabel("Patient Database Registry");
        sectionTitle.setBounds(35, 22, 400, 30);
        sectionTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        sectionTitle.setForeground(new Color(18, 24, 38)); // Dark charcoal/navy color
        panel.add(sectionTitle);

        // Modern Clean JTable Setup
        JTable table = new JTable();
        table.setBackground(Color.WHITE);
        table.setForeground(new Color(55, 65, 81)); // Clean readability body text
        table.setSelectionBackground(new Color(230, 247, 255)); // Soft light-cyan row selection tint
        table.setSelectionForeground(new Color(0, 102, 204));
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.setRowHeight(38); // Comfortable vertical padding
        table.setShowGrid(false); // Clean gridless look
        table.setIntercellSpacing(new Dimension(0, 0));

        // Designing the Header Control Area using the light theme layout
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));
        header.setBackground(new Color(240, 244, 248)); // Tinted off-white background
        header.setForeground(new Color(100, 116, 139)); // Slate colored muted headings
        header.setBorder(new LineBorder(new Color(226, 232, 240), 1));

        // Safe database fetching logic blocks
        try {
            conn c = new conn();
            String q = "select * from Patient_Info";
            ResultSet resultSet = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Custom renderer for cell spacing adjustments and side alignments
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.LEFT);
        cellRenderer.setBorder(BorderFactory.createEmptyBorder(0, 12, 0, 0));
        table.setDefaultRenderer(Object.class, cellRenderer);

        // ScrollPane encapsulation layer
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(35, 75, 820, 410);
        scrollPane.setBackground(Color.WHITE);
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setBorder(new LineBorder(new Color(226, 232, 240), 1));
        panel.add(scrollPane);

        // Modern flat styled navigation action control button
        JButton button = new JButton("Close View");
        button.setBounds(705, 515, 150, 38);
        button.setFont(new Font("Segoe UI", Font.BOLD, 13));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(79, 70, 229)); // Clean bright primary accent action blue/indigo
        button.setBorder(null);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
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