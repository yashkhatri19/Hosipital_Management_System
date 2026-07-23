package hospital.management.system;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;


public class Reception extends JFrame {

    Reception() {
        // Main Window Management - Full Canvas Screen Dark Theme
        JPanel mainCanvas = new JPanel();
        mainCanvas.setBounds(0, 0, 1540, 850);
        mainCanvas.setBackground(new Color(18, 22, 26)); // Cyber Dark Background
        mainCanvas.setLayout(null);
        add(mainCanvas);

        // LEFT NAVIGATION BAR DOCK
        JPanel navDock = new JPanel();
        navDock.setBounds(0, 0, 300, 850);
        navDock.setBackground(new Color(27, 32, 40)); // Matte dark finish 
        navDock.setLayout(null);
        mainCanvas.add(navDock);

        // Application Branding Title inside Navbar
        JLabel appTitle = new JLabel("HOSPITAL OS");
        appTitle.setBounds(35, 40, 230, 30);
        appTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        appTitle.setForeground(new Color(230, 235, 245));
        navDock.add(appTitle);
        // Application Subtitle for context
        JLabel appSub = new JLabel("Reception Terminal Workspace");
        appSub.setBounds(35, 70, 230, 20);
        appSub.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        appSub.setForeground(new Color(110, 125, 140));
        navDock.add(appSub);

        // TOP CONTAINER PANEL FOR BRANDING VISUALS
        JPanel topPanel = new JPanel();
        topPanel.setBounds(320, 20, 1190, 120);
        topPanel.setBackground(new Color(27, 32, 40));
        topPanel.setLayout(null);
        mainCanvas.add(topPanel);
        // Decorative Neon Accent Line
        JLabel welcomeMsg = new JLabel("System Overview & Operations");
        welcomeMsg.setBounds(30, 45, 400, 30);
        welcomeMsg.setFont(new Font("Segoe UI", Font.BOLD, 22));
        welcomeMsg.setForeground(Color.WHITE);
        topPanel.add(welcomeMsg);

        // --- DYNAMICALLY MOVED LOGOUT TERMINAL TO TOP RIGHT ---
        JButton logoutButton = new JButton("Logout Terminal");
        logoutButton.setBounds(980, 40, 180, 42); // Clean top-right alignment
        logoutButton.setFont(new Font("Segoe UI", Font.BOLD, 13));
        logoutButton.setFocusPainted(false);
        logoutButton.setBackground(new Color(27, 32, 40));
        logoutButton.setForeground(new Color(244, 67, 54));
        logoutButton.setBorder(new LineBorder(new Color(244, 67, 54), 1));
        logoutButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        topPanel.add(logoutButton);
        // Logout action listener to return to login screen
        logoutButton.addActionListener(e -> {
            setVisible(false);
            new Login();
        });

        // CENTRAL AREA FOR DYNAMIC FORMS / CONTENT WINDOWS
        JPanel centralDisplay = new JPanel();
        centralDisplay.setBounds(320, 160, 1190, 640);
        centralDisplay.setBackground(new Color(27, 32, 40));
        centralDisplay.setBorder(new LineBorder(new Color(40, 50, 65), 1));
        centralDisplay.setLayout(null);
        mainCanvas.add(centralDisplay);

        // Decorative Neon Metric Card Placeholder like the reference chart area
        JPanel mockCard = new JPanel();
        mockCard.setBounds(40, 40, 1110, 560);
        mockCard.setBackground(new Color(22, 27, 34));
        mockCard.setBorder(new LineBorder(new Color(156, 39, 176, 100), 1)); // Soft Purple border
        centralDisplay.add(mockCard);

        // UTILITY METHOD TO CREATING STREAMLINED FLAT NAVIGATION BUTTONS (Reduced size to 9 items)
        String[] btnLabels = {
            "Add New Patient", "Room Status", "Department Info", 
            "All Employee Info", "Patient Records", "Patient Discharge", 
            "Update Details", "Hospital Ambulance", "Search Room"
        };
        // Array to hold buttons for dynamic creation
        JButton[] buttons = new JButton[9];
        int yOffset = 150; // Dynamic spacing alignment 
        // Loop to create and style buttons with consistent design
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton(btnLabels[i]);
            buttons[i].setBounds(25, yOffset, 250, 42);
            buttons[i].setFont(new Font("Segoe UI", Font.BOLD, 13));
            buttons[i].setFocusPainted(false);
            buttons[i].setHorizontalAlignment(SwingConstants.LEFT);
            buttons[i].setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
            buttons[i].setBackground(new Color(36, 44, 54));
            buttons[i].setForeground(new Color(200, 210, 230));
            buttons[i].setBorder(new LineBorder(new Color(50, 62, 78), 1));
            buttons[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
            
            navDock.add(buttons[i]);
            yOffset += 55; // Multiplier increment
        }

        // Action Trigger Configuration mappings
        buttons[0].addActionListener(e -> new NEW_PATIENT());
        buttons[1].addActionListener(e -> new Room());
        buttons[2].addActionListener(e -> new Department());
        buttons[3].addActionListener(e -> new Employee_info());
        buttons[4].addActionListener(e -> new ALL_Patient_Info());
        buttons[5].addActionListener(e -> new patient_discharge());
        buttons[6].addActionListener(e -> new update_patient_details());
        buttons[7].addActionListener(e -> new Ambulance());
        buttons[8].addActionListener(e -> new SearchRoom());

        // Frame configuration setups
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Dynamic auto full-screen scale
        setUndecorated(true);
        setLayout(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Reception();
    }
}