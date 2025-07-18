import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class HospitalManagementSystem {
    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private ArrayList<Patient> patients = new ArrayList<>();
    private ArrayList<Appointment> appointments = new ArrayList<>();
    private ArrayList<EHR> ehrs = new ArrayList<>();
    private ArrayList<Billing> billings = new ArrayList<>();
    private ArrayList<Inventory> inventories = new ArrayList<>();
    private ArrayList<Staff> staff = new ArrayList<>();
    private int patientIdCounter = 1;
    private int appointmentIdCounter = 1;

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

        @Override
        public String toString() {
            return "Patient ID: " + id + ", Name: " + name + ", Contact: " + contact;
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

        @Override
        public String toString() {
            return "Appointment ID: " + id + ", Patient: " + patient.name + ", Date: " + new SimpleDateFormat("MM/dd/yyyy HH:mm").format(dateTime) + ", Doctor: " + doctor;
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

        @Override
        public String toString() {
            return "Patient ID: " + patientId + ", Diagnosis: " + diagnosis + ", Treatment: " + treatment;
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

        @Override
        public String toString() {
            return "Item: " + itemName + ", Quantity: " + quantity + ", Price: $" + price;
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

        @Override
        public String toString() {
            return "Staff ID: " + id + ", Name: " + name + ", Role: " + role;
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

        @Override
        public String toString() {
            return "Patient ID: " + patientId + ", Amount: $" + amount + ", Date: " + new SimpleDateFormat("MM/dd/yyyy").format(date);
        }
    }

    public HospitalManagementSystem() {
        // Initial data
        patients.add(new Patient(1, "John Doe", "123-456-7890"));
        inventories.add(new Inventory("Bandage", 100, 5.0));
        staff.add(new Staff(1, "Dr. Smith", "Doctor"));
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

        // Patient Registration Panel
        JPanel patientRegPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridwidth = GridBagConstraints.REMAINDER;

        JLabel regTitle = new JLabel("Patient Registration", SwingConstants.CENTER);
        regTitle.setFont(new Font("Arial", Font.BOLD, 18));
        JTextField nameField = new JTextField(15);
        JTextField contactField = new JTextField(15);
        JButton regSubmit = new JButton("Register");
        JTextArea regDisplay = new JTextArea(5, 30);
        regDisplay.setEditable(false);

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
        patientRegPanel.add(new JScrollPane(regDisplay), gbc);

        regSubmit.addActionListener(e -> {
            String name = nameField.getText().trim();
            String contact = contactField.getText().trim();
            if (!name.isEmpty() && !contact.isEmpty()) {
                Patient patient = new Patient(patientIdCounter++, name, contact);
                patients.add(patient);
                regDisplay.setText("Registered Patients:\n" + String.join("\n", patients.stream().map(Patient::toString).toArray(String[]::new)));
                regDisplay.setForeground(Color.BLACK);
            } else {
                regDisplay.setText("Please fill all fields!");
                regDisplay.setForeground(Color.RED);
            }
            nameField.setText("");
            contactField.setText("");
        });

        // Appointment Scheduling Panel
        JPanel appointmentPanel = new JPanel(new GridBagLayout());
        JLabel appTitle = new JLabel("Appointment Scheduling", SwingConstants.CENTER);
        appTitle.setFont(new Font("Arial", Font.BOLD, 18));
        JTextField patientIdField = new JTextField(5);
        JTextField doctorField = new JTextField(15);
        JTextField dateField = new JTextField("MM/dd/yyyy HH:mm", 15);
        JButton appSubmit = new JButton("Schedule");
        JTextArea appDisplay = new JTextArea(5, 30);
        appDisplay.setEditable(false);

        gbc.gridy = 0;
        appointmentPanel.add(appTitle, gbc);
        gbc.gridy = 1;
        appointmentPanel.add(new JLabel("Patient ID:"), gbc);
        gbc.gridy = 2;
        appointmentPanel.add(patientIdField, gbc);
        gbc.gridy = 3;
        appointmentPanel.add(new JLabel("Doctor:"), gbc);
        gbc.gridy = 4;
        appointmentPanel.add(doctorField, gbc);
        gbc.gridy = 5;
        appointmentPanel.add(new JLabel("Date/Time:"), gbc);
        gbc.gridy = 6;
        appointmentPanel.add(dateField, gbc);
        gbc.gridy = 7;
        appointmentPanel.add(appSubmit, gbc);
        gbc.gridy = 8;
        appointmentPanel.add(new JScrollPane(appDisplay), gbc);

        appSubmit.addActionListener(e -> {
            try {
                int id = Integer.parseInt(patientIdField.getText().trim());
                Patient patient = patients.stream().filter(p -> p.id == id).findFirst().orElse(null);
                if (patient != null) {
                    Date date = new SimpleDateFormat("MM/dd/yyyy HH:mm").parse(dateField.getText().trim());
                    String doctor = doctorField.getText().trim();
                    if (!doctor.isEmpty()) {
                        Appointment appointment = new Appointment(appointmentIdCounter++, patient, date, doctor);
                        appointments.add(appointment);
                        appDisplay.setText("Appointments:\n" + String.join("\n", appointments.stream().map(Appointment::toString).toArray(String[]::new)));
                        appDisplay.setForeground(Color.BLACK);
                    } else {
                        appDisplay.setText("Please enter a doctor name!");
                        appDisplay.setForeground(Color.RED);
                    }
                } else {
                    appDisplay.setText("Patient ID not found!");
                    appDisplay.setForeground(Color.RED);
                }
            } catch (Exception ex) {
                appDisplay.setText("Invalid date format! Use MM/dd/yyyy HH:mm");
                appDisplay.setForeground(Color.RED);
            }
            patientIdField.setText("");
            doctorField.setText("");
            dateField.setText("MM/dd/yyyy HH:mm");
        });

        // EHR Panel
        JPanel ehrPanel = new JPanel(new GridBagLayout());
        JLabel ehrTitle = new JLabel("Electronic Health Records", SwingConstants.CENTER);
        ehrTitle.setFont(new Font("Arial", Font.BOLD, 18));
        JTextField ehrPatientId = new JTextField(5);
        JTextField diagnosisField = new JTextField(15);
        JTextField treatmentField = new JTextField(15);
        JButton ehrSubmit = new JButton("Add Record");
        JTextArea ehrDisplay = new JTextArea(5, 30);
        ehrDisplay.setEditable(false);

        gbc.gridy = 0;
        ehrPanel.add(ehrTitle, gbc);
        gbc.gridy = 1;
        ehrPanel.add(new JLabel("Patient ID:"), gbc);
        gbc.gridy = 2;
        ehrPanel.add(ehrPatientId, gbc);
        gbc.gridy = 3;
        ehrPanel.add(new JLabel("Diagnosis:"), gbc);
        gbc.gridy = 4;
        ehrPanel.add(diagnosisField, gbc);
        gbc.gridy = 5;
        ehrPanel.add(new JLabel("Treatment:"), gbc);
        gbc.gridy = 6;
        ehrPanel.add(treatmentField, gbc);
        gbc.gridy = 7;
        ehrPanel.add(ehrSubmit, gbc);
        gbc.gridy = 8;
        ehrPanel.add(new JScrollPane(ehrDisplay), gbc);

        ehrSubmit.addActionListener(e -> {
            try {
                int id = Integer.parseInt(ehrPatientId.getText().trim());
                Patient patient = patients.stream().filter(p -> p.id == id).findFirst().orElse(null);
                if (patient != null) {
                    String diagnosis = diagnosisField.getText().trim();
                    String treatment = treatmentField.getText().trim();
                    if (!diagnosis.isEmpty() && !treatment.isEmpty()) {
                        EHR ehr = new EHR(id, diagnosis, treatment);
                        ehrs.add(ehr);
                        ehrDisplay.setText("EHR Records:\n" + String.join("\n", ehrs.stream().map(EHR::toString).toArray(String[]::new)));
                        ehrDisplay.setForeground(Color.BLACK);
                    } else {
                        ehrDisplay.setText("Please fill all fields!");
                        ehrDisplay.setForeground(Color.RED);
                    }
                } else {
                    ehrDisplay.setText("Patient ID not found!");
                    ehrDisplay.setForeground(Color.RED);
                }
            } catch (NumberFormatException ex) {
                ehrDisplay.setText("Invalid Patient ID!");
                ehrDisplay.setForeground(Color.RED);
            }
            ehrPatientId.setText("");
            diagnosisField.setText("");
            treatmentField.setText("");
        });

        // Billing Panel
        JPanel billingPanel = new JPanel(new GridBagLayout());
        JLabel billTitle = new JLabel("Billing & Invoicing", SwingConstants.CENTER);
        billTitle.setFont(new Font("Arial", Font.BOLD, 18));
        JTextField billPatientId = new JTextField(5);
        JTextField amountField = new JTextField(10);
        JButton billSubmit = new JButton("Generate Bill");
        JTextArea billDisplay = new JTextArea(5, 30);
        billDisplay.setEditable(false);

        gbc.gridy = 0;
        billingPanel.add(billTitle, gbc);
        gbc.gridy = 1;
        billingPanel.add(new JLabel("Patient ID:"), gbc);
        gbc.gridy = 2;
        billingPanel.add(billPatientId, gbc);
        gbc.gridy = 3;
        billingPanel.add(new JLabel("Amount:"), gbc);
        gbc.gridy = 4;
        billingPanel.add(amountField, gbc);
        gbc.gridy = 5;
        billingPanel.add(billSubmit, gbc);
        gbc.gridy = 6;
        billingPanel.add(new JScrollPane(billDisplay), gbc);

        billSubmit.addActionListener(e -> {
            try {
                int id = Integer.parseInt(billPatientId.getText().trim());
                Patient patient = patients.stream().filter(p -> p.id == id).findFirst().orElse(null);
                if (patient != null) {
                    double amount = Double.parseDouble(amountField.getText().trim());
                    if (amount > 0) {
                        Billing bill = new Billing(id, amount, new Date());
                        billings.add(bill);
                        billDisplay.setText("Bills:\n" + String.join("\n", billings.stream().map(Billing::toString).toArray(String[]::new)));
                        billDisplay.setForeground(Color.BLACK);
                    } else {
                        billDisplay.setText("Amount must be positive!");
                        billDisplay.setForeground(Color.RED);
                    }
                } else {
                    billDisplay.setText("Patient ID not found!");
                    billDisplay.setForeground(Color.RED);
                }
            } catch (NumberFormatException ex) {
                billDisplay.setText("Invalid amount or Patient ID!");
                billDisplay.setForeground(Color.RED);
            }
            billPatientId.setText("");
            amountField.setText("");
        });

        // Inventory Management Panel
        JPanel inventoryPanel = new JPanel(new GridBagLayout());
        JLabel invTitle = new JLabel("Inventory Management", SwingConstants.CENTER);
        invTitle.setFont(new Font("Arial", Font.BOLD, 18));
        JTextField itemField = new JTextField(15);
        JTextField quantityField = new JTextField(5);
        JTextField priceField = new JTextField(10);
        JButton invAdd = new JButton("Add Item");
        JButton invUpdate = new JButton("Update Quantity");
        JTextArea invDisplay = new JTextArea(5, 30);
        invDisplay.setEditable(false);

        gbc.gridy = 0;
        inventoryPanel.add(invTitle, gbc);
        gbc.gridy = 1;
        inventoryPanel.add(new JLabel("Item Name:"), gbc);
        gbc.gridy = 2;
        inventoryPanel.add(itemField, gbc);
        gbc.gridy = 3;
        inventoryPanel.add(new JLabel("Quantity:"), gbc);
        gbc.gridy = 4;
        inventoryPanel.add(quantityField, gbc);
        gbc.gridy = 5;
        inventoryPanel.add(new JLabel("Price:"), gbc);
        gbc.gridy = 6;
        inventoryPanel.add(priceField, gbc);
        gbc.gridy = 7;
        inventoryPanel.add(invAdd, gbc);
        gbc.gridy = 8;
        inventoryPanel.add(invUpdate, gbc);
        gbc.gridy = 9;
        inventoryPanel.add(new JScrollPane(invDisplay), gbc);

        invAdd.addActionListener(e -> {
            String item = itemField.getText().trim();
            try {
                int qty = Integer.parseInt(quantityField.getText().trim());
                double price = Double.parseDouble(priceField.getText().trim());
                if (!item.isEmpty() && qty > 0 && price > 0) {
                    inventories.add(new Inventory(item, qty, price));
                    invDisplay.setText("Inventory:\n" + String.join("\n", inventories.stream().map(Inventory::toString).toArray(String[]::new)));
                    invDisplay.setForeground(Color.BLACK);
                } else {
                    invDisplay.setText("Invalid input! Ensure positive quantity and price.");
                    invDisplay.setForeground(Color.RED);
                }
            } catch (NumberFormatException ex) {
                invDisplay.setText("Invalid quantity or price!");
                invDisplay.setForeground(Color.RED);
            }
            itemField.setText("");
            quantityField.setText("");
            priceField.setText("");
        });

        invUpdate.addActionListener(e -> {
            String item = itemField.getText().trim();
            try {
                int qtyChange = Integer.parseInt(quantityField.getText().trim());
                Inventory inv = inventories.stream().filter(i -> i.itemName.equalsIgnoreCase(item)).findFirst().orElse(null);
                if (inv != null && inv.updateQuantity(qtyChange)) {
                    invDisplay.setText("Inventory:\n" + String.join("\n", inventories.stream().map(Inventory::toString).toArray(String[]::new)));
                    invDisplay.setForeground(Color.BLACK);
                } else {
                    invDisplay.setText("Item not found or insufficient stock!");
                    invDisplay.setForeground(Color.RED);
                }
            } catch (NumberFormatException ex) {
                invDisplay.setText("Invalid quantity!");
                invDisplay.setForeground(Color.RED);
            }
            itemField.setText("");
            quantityField.setText("");
            priceField.setText("");
        });

        // Staff Management Panel
        JPanel staffPanel = new JPanel(new GridBagLayout());
        JLabel staffTitle = new JLabel("Staff Management", SwingConstants.CENTER);
        staffTitle.setFont(new Font("Arial", Font.BOLD, 18));
        JTextField staffName = new JTextField(15);
        JTextField roleField = new JTextField(15);
        JButton staffAdd = new JButton("Add Staff");
        JTextArea staffDisplay = new JTextArea(5, 30);
        staffDisplay.setEditable(false);

        gbc.gridy = 0;
        staffPanel.add(staffTitle, gbc);
        gbc.gridy = 1;
        staffPanel.add(new JLabel("Name:"), gbc);
        gbc.gridy = 2;
        staffPanel.add(staffName, gbc);
        gbc.gridy = 3;
        staffPanel.add(new JLabel("Role:"), gbc);
        gbc.gridy = 4;
        staffPanel.add(roleField, gbc);
        gbc.gridy = 5;
        staffPanel.add(staffAdd, gbc);
        gbc.gridy = 6;
        staffPanel.add(new JScrollPane(staffDisplay), gbc);

        staffAdd.addActionListener(e -> {
            String name = staffName.getText().trim();
            String role = roleField.getText().trim();
            if (!name.isEmpty() && !role.isEmpty()) {
                Staff member = new Staff(staff.size() + 1, name, role);
                staff.add(member);
                staffDisplay.setText("Staff:\n" + String.join("\n", staff.stream().map(Staff::toString).toArray(String[]::new)));
                staffDisplay.setForeground(Color.BLACK);
            } else {
                staffDisplay.setText("Please fill all fields!");
                staffDisplay.setForeground(Color.RED);
            }
            staffName.setText("");
            roleField.setText("");
        });

        // Button actions
        patientRegButton.addActionListener(e -> cardLayout.show(mainPanel, "patientReg"));
        appointmentButton.addActionListener(e -> cardLayout.show(mainPanel, "appointment"));
        ehrButton.addActionListener(e -> cardLayout.show(mainPanel, "ehr"));
        billingButton.addActionListener(e -> cardLayout.show(mainPanel, "billing"));
        inventoryButton.addActionListener(e -> cardLayout.show(mainPanel, "inventory"));
        staffButton.addActionListener(e -> cardLayout.show(mainPanel, "staff"));

        // Add panels to main panel
        mainPanel.add(menuPanel, "mainMenu");
        mainPanel.add(patientRegPanel, "patientReg");
        mainPanel.add(appointmentPanel, "appointment");
        mainPanel.add(ehrPanel, "ehr");
        mainPanel.add(billingPanel, "billing");
        mainPanel.add(inventoryPanel, "inventory");
        mainPanel.add(staffPanel, "staff");

        frame.add(mainPanel);
        cardLayout.show(mainPanel, "mainMenu");
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(HospitalManagementSystem::new);
    }
}