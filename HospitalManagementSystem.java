import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;

public class HospitalManagementSystem {
    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    // Patient class
    static class Patient {
        private int id;
        private String name;
        private String contact;

        public Patient(int id, String name, String contact) {
            this.id = id;
            this.name = name;
            this.contact = contact;
        }
    }

    // Appointment class
    static class Appointment {
        private int id;
        private Patient patient;
        private Date dateTime;
        private String doctor;

        public Appointment(int id, Patient patient, Date dateTime, String doctor) {
            this.id = id;
            this.patient = patient;
            this.dateTime = dateTime;
            this.doctor = doctor;
        }
    }

    // EHR class
    static class EHR {
        private int patientId;
        private String diagnosis;
        private String treatment;

        public EHR(int patientId, String diagnosis, String treatment) {
            this.patientId = patientId;
            this.diagnosis = diagnosis;
            this.treatment = treatment;
        }
    }

    // Inventory class
    static class Inventory {
        private String itemName;
        private int quantity;
        private double price;

        public Inventory(String itemName, int quantity, double price) {
            this.itemName = itemName;
            this.quantity = quantity;
            this.price = price;
        }

        public boolean updateQuantity(int amount) {
            if (quantity + amount >= 0) {
                quantity += amount;
                return true;
            }
            return false;
        }
    }

    // Staff class
    static class Staff {
        private int id;
        private String name;
        private String role;

        public Staff(int id, String name, String role) {
            this.id = id;
            this.name = name;
            this.role = role;
        }
    }

    // Billing class
    static class Billing {
        private int patientId;
        private double amount;
        private Date date;

        public Billing(int patientId, double amount, Date date) {
            this.patientId = patientId;
            this.amount = amount;
            this.date = date;
        }
    }

    public HospitalManagementSystem() {
        initializeUI();
    }

    private void initializeUI() {
        frame = new JFrame("Hospital Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Main Menu Panel
        JPanel menuPanel = new JPanel(new GridLayout(6, 1, 10, 10));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JLabel menuTitle = new JLabel("Hospital Management", SwingConstants.CENTER);
        menuTitle.setFont(new Font("Arial", Font.BOLD, 18));
        JButton patientRegButton = new JButton("Patient Registration");
        JButton appointmentButton = new JButton("Appointment Scheduling");
        JButton ehrButton = new JButton("Electronic Health Records");
        JButton billingButton = new JButton("Billing & Invoicing");
        JButton inventoryButton = new JButton("Inventory Management");
        JButton staffButton = new JButton("Staff Management");

        menuPanel.add(menuTitle);
        menuPanel.add(patientRegButton);
        menuPanel.add(appointmentButton);
        menuPanel.add(ehrButton);
        menuPanel.add(billingButton);
        menuPanel.add(inventoryButton);
        menuPanel.add(staffButton);

        // Patient Registration Panel (Example)
        JPanel patientRegPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridwidth = GridBagConstraints.REMAINDER;

        JLabel regTitle = new JLabel("Patient Registration", SwingConstants.CENTER);
        regTitle.setFont(new Font("Arial", Font.BOLD, 18));
        JTextField nameField = new JTextField(15);
        JTextField contactField = new JTextField(15);
        JButton regSubmit = new JButton("Register");
        JLabel regMessage = new JLabel("", SwingConstants.CENTER);

        gbc.gridy = 0;
        patientRegPanel.add(regTitle, gbc);
        gbc.gridy = 1;
        patientRegPanel.add(new JLabel("Name:"), gbc);
        gbc.gridy = 2;
        patientRegPanel.add(nameField, gbc);
        gbc.gridy = 3;
        patientRegPanel.add(new JLabel("Contact:"), gbc);
        gbc.gridy = 4;
        patientRegPanel.add(contactField, gbc);
        gbc.gridy = 5;
        patientRegPanel.add(regSubmit, gbc);
        gbc.gridy = 6;
        patientRegPanel.add(regMessage, gbc);

        regSubmit.addActionListener(e -> {
            String name = nameField.getText();
            String contact = contactField.getText();
            if (!name.isEmpty() && !contact.isEmpty()) {
                Patient patient = new Patient(1, name, contact); // Simple ID assignment
                regMessage.setText("Patient registered successfully!");
                regMessage.setForeground(Color.GREEN);
            } else {
                regMessage.setText("Please fill all fields!");
                regMessage.setForeground(Color.RED);
            }
            nameField.setText("");
            contactField.setText("");
        });

        // Add more panels for other modules similarly (simplified for brevity)

        // Add panels to main panel
        mainPanel.add(menuPanel, "mainMenu");
        mainPanel.add(patientRegPanel, "patientReg");

        frame.add(mainPanel);
        cardLayout.show(mainPanel, "mainMenu");
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(HospitalManagementSystem::new);
    }
}